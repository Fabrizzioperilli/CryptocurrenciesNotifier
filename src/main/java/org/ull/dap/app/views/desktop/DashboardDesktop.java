package org.ull.dap.app.views.desktop;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.ull.dap.app.models.entities.Asset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

import static org.ull.dap.app.views.desktop.ViewDesktop.ROUTE_IMAGE_LOGO;

/**
 * The type Dashboard desktop.
 */
public class DashboardDesktop extends JFrame {

    private final XYSeries bitcoinSeries;
    private final XYSeries ethereumSeries;

    private final DefaultPieDataset datasetPieChart;

    private final DefaultCategoryDataset datasetBarChart;

    /**
     * Instantiates a new Dashboard desktop.
     *
     * @param title the title
     */
    public DashboardDesktop(String title) {
        super("CryptoDashboard");

        // Crear series para las gráficas
        bitcoinSeries = new XYSeries("Bitcoin");
        ethereumSeries = new XYSeries("Ethereum");
        datasetPieChart = new DefaultPieDataset();
        datasetBarChart = new DefaultCategoryDataset();

        // Agregar series a los conjuntos de datos
        XYSeriesCollection bitcoinDataset = new XYSeriesCollection(bitcoinSeries);
        XYSeriesCollection ethereumDataset = new XYSeriesCollection(ethereumSeries);

        // Crear gráficas
        JFreeChart bitcoinChart = ChartFactory.createTimeSeriesChart(
                "Bitcoin",
                "Time",
                "Price (USD)",
                bitcoinDataset
        );

        JFreeChart ethereumChart = ChartFactory.createTimeSeriesChart(
                "Ethereum",
                "Time",
                "Price (USD)",
                ethereumDataset
        );


        JFreeChart pieChart = ChartFactory.createPieChart(
                "Supply",
                datasetPieChart,
                true,
                true,
                false
        );

        JFreeChart barChart = ChartFactory.createBarChart(
                "Change 24h",
                "Assets",
                "Percentage %",
                datasetBarChart
        );

        customizeChartAxis(bitcoinChart);
        customizeChartAxis(ethereumChart);

        customizeChartRenderer(bitcoinChart, Color.WHITE);
        customizeChartRenderer(ethereumChart, Color.BLUE);

        ChartPanel bitcoinPanel = new ChartPanel(bitcoinChart);
        ChartPanel ethereumPanel = new ChartPanel(ethereumChart);
        ChartPanel piePanel = new ChartPanel(pieChart);
        ChartPanel barPanel = new ChartPanel(barChart);

        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(bitcoinPanel);
        panel.add(piePanel);
        panel.add(ethereumPanel);
        panel.add(barPanel);
        getContentPane().add(panel);

        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1500, 700);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(ROUTE_IMAGE_LOGO))).getImage());
    }

    private void customizeChartAxis(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = new DateAxis();
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        plot.setDomainAxis(dateAxis);
    }

    private void customizeChartRenderer(JFreeChart chart, Color color) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(2.2f));
        renderer.setSeriesPaint(0, color);
        plot.setRenderer(renderer);
    }

    /**
     * Update data.
     *
     * @param assets the assets
     */
    public void updateData(List<Asset> assets) {
        long currentTime = System.currentTimeMillis();
        for (Asset asset : assets) {
            if ("bitcoin".equals(asset.getData().getId())) {
                bitcoinSeries.addOrUpdate(currentTime, asset.getData().getPriceUsd());
            } else if ("ethereum".equals(asset.getData().getId())) {
                ethereumSeries.addOrUpdate(currentTime, asset.getData().getPriceUsd());
            }
            datasetPieChart.setValue(asset.getData().getName(), asset.getData().getSupply());
            datasetBarChart.setValue(asset.getData().getChangePercent24Hr(), "", asset.getData().getName());
        }
    }
}