package currency.ui.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import currency.database.record.model.Record;

public class GraphPanel extends JPanel {
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Record> records;
	
    public void init() {
    	records = new ArrayList<Record>();
    }
    
	public void setRecords(List<Record> records) {
		this.records = records;
		invalidate();
        this.repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (getMaxX() - getMinX());
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxY() - getMinY());
 
        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            int x1 = (int) ((getMaxX() - records.get(i).getTimestamp()) * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxY() - records.get(i).getValue()) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
 
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);
 
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (records.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinY() + (getMaxY() - getMinY()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }
 
        for (int i = 0; i < records.size(); i++) {
            if (records.size() > 1) {
                if ((i % ((int) ((records.size() / 10.0)) + 1)) == 0) {
                	int x0 = i * (getWidth() - padding * 2 - labelPadding) / (records.size() - 1) + padding + labelPadding;
                    int x1 = x0;
                    int y0 = getHeight() - padding - labelPadding;
                    int y1 = y0 - pointWidth;
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    Timestamp stamp = new Timestamp(records.get(i).getTimestamp());
                    Date date = new Date(stamp.getTime());
                    String xLabel = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    g2.drawLine(x0, y0, x1, y1);
                }
            }
        }
 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
 
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
 
        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }
	
    private double getMinY() {
        double minY = Double.MAX_VALUE;
        for (Record record : records) {
        	minY = Math.min(minY, record.getValue());
        }
        return minY;
    }
 
    private double getMaxY() {
        double maxY = Double.MIN_VALUE;
        for (Record record : records) {
        	maxY = Math.max(maxY, record.getValue());
        }
        return maxY;
    }
    
    private double getMinX() {
        double minX = Double.MAX_VALUE;
        for (Record record : records) {
        	minX = Math.min(minX, record.getTimestamp());
        }
        return minX;
    }
 
    private double getMaxX() {
        double maxX = Double.MIN_VALUE;
        for (Record record : records) {
        	maxX = Math.max(maxX, record.getTimestamp());
        }
        return maxX;
    }
	
 
}
