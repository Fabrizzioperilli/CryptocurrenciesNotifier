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

public class DashboardDesktop extends JFrame {

    private final XYSeries bitcoinSeries;
    private final XYSeries ethereumSeries;

    private final DefaultPieDataset datasetPieChart;

    private final DefaultCategoryDataset datasetBarChart;

    public DashboardDesktop(String title) {
        super("Crypto Dashboard");

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
                "Tiempo",
                "Precio",
                bitcoinDataset
        );

        JFreeChart ethereumChart = ChartFactory.createTimeSeriesChart(
                "Ethereum",
                "Tiempo",
                "Precio",
                ethereumDataset
        );


        JFreeChart pieChart = ChartFactory.createPieChart(
                "Acciones en circulación",
                datasetPieChart,
                true,
                true,
                false
        );

        JFreeChart barChart = ChartFactory.createBarChart(
                "Porcentaje de cambio de 24h",
                "Activos",
                "Porcentaje",
                datasetBarChart
        );

        // Personalizar el eje X para mostrar fechas de manera más clara
        customizeChartAxis(bitcoinChart);
        customizeChartAxis(ethereumChart);

        customizeChartRenderer(bitcoinChart);
        customizeChartRenderer(ethereumChart);

        ChartPanel bitcoinPanel = new ChartPanel(bitcoinChart);
        ChartPanel ethereumPanel = new ChartPanel(ethereumChart);
        ChartPanel piePanel = new ChartPanel(pieChart);
        ChartPanel barPanel = new ChartPanel(barChart);

        JPanel panel = new JPanel(new GridLayout(2, 3));
        panel.add(bitcoinPanel);
        panel.add(ethereumPanel);
        panel.add(piePanel);
        panel.add(barPanel);

        // Configurar la interfaz
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1500, 700);
    }

    private void customizeChartAxis(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = new DateAxis();
        dateAxis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        plot.setDomainAxis(dateAxis);
    }

    private void customizeChartRenderer(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(3.0f)); // Grosor de la línea
        plot.setRenderer(renderer);
    }

    public void updateData(List<Asset> assets) {
        // Actualizar datos de las gráficas con los nuevos datos de la API
        long currentTime = System.currentTimeMillis();
        for (Asset asset : assets) {
            if ("bitcoin".equals(asset.getData().getId())) {
                bitcoinSeries.addOrUpdate(currentTime, asset.getData().getPriceUsd());
                datasetPieChart.setValue("Bitcoin", asset.getData().getSupply());
                datasetBarChart.setValue(asset.getData().getChangePercent24Hr(), "Porcentaje", "Bitcoin");
            } else if ("ethereum".equals(asset.getData().getId())) {
                ethereumSeries.addOrUpdate(currentTime, asset.getData().getPriceUsd());
                datasetPieChart.setValue("Ethereum", asset.getData().getSupply());
                datasetBarChart.setValue(asset.getData().getChangePercent24Hr(), "Porcentaje", "Ethereum");
            } else if ("litecoin".equals(asset.getData().getId())) {
                datasetPieChart.setValue("Litecoin", asset.getData().getSupply());
                datasetBarChart.setValue(asset.getData().getChangePercent24Hr(), "Porcentaje", "Litecoin");
            }
        }
    }
}