/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecuaciones_de_segundo_grado;

/**
 *
 * @author LABORATORIO
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FunctionVisualizer extends JFrame implements ActionListener {

    // define X range
    public int minX = -5;
    public int maxX = 6;
    ;
    JFreeChart chart;

    public FunctionVisualizer(String title) {
        super(title);
        // Create a simple XY chart
        XYSeries series = new XYSeries("function");
        for (int i = minX; i < maxX; i = i + 1) {
            series.add(i, f(i));
            System.out.println("x=" + i + " Y=" + f(i));

        }

        // Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        String a = formulario.a_cuadratica.getText();
        String b = formulario.b_cuadratica.getText();
        String c = formulario.c_cuadratica.getText();
        String ax;
         String bx;
          String cx;
 String fx ;
        if (buscadorDeNegativos(a) > 0) {
            
           ax  = "-"+a+"x^2";
           
        }
        else{
          ax = ""+a+"x^2";
        }
           if (buscadorDeNegativos(b) > 0) {
            
            bx = ""+b+"x";
           
        }
            else{
          bx = "+"+b+"x";
        }
              if (buscadorDeNegativos(c) > 0) {
            
            cx = ""+c + "=0";
           
        }
               else{
          cx = "+"+c + "=0";
        }
       
                fx=ax+bx+cx;
        

        // Generate the graph
        chart = ChartFactory.createXYLineChart(
                fx, // Title
                "x", // x-axis Label
                "f(x)", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        ChartPanel panel = new ChartPanel(chart);

        JButton saveButton = new JButton("Save...");
        saveButton.addActionListener(this);

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panel);
        getContentPane().add(saveButton);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private double f(int x) {
        Double a = Double.parseDouble(formulario.a_cuadratica.getText());
        Double b = Double.parseDouble(formulario.b_cuadratica.getText());
        Double c = Double.parseDouble(formulario.c_cuadratica.getText());
        // define your function

        //Double x_2=Math.pow(x, 3);
        Double x_2 = Math.pow(x, 2);
        // return (Math.log((double)1/x));
        // String d ="ax^2+bx+c=0";
        Double ff = a * x_2 + b * x + c;
        //   System.out.println(ff);
        return ff;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser("C:/");
        fc.setCurrentDirectory(new File("C:/"));
        fc.setSelectedFile(new File("C:/chart1.jpg"));
        int ret = fc.showSaveDialog(null);
        if (ret == fc.APPROVE_OPTION) {
            try {
                System.out.print("Saving... " + fc.getSelectedFile().getAbsolutePath() + "\n");
                ChartUtilities.saveChartAsJPEG(fc.getSelectedFile(), chart, 500, 300);
            } catch (IOException IOe) {
                System.err.println("Problem occurred creating chart.");
                IOe.printStackTrace();
            }
        }

    }

    private int buscadorDeNegativos(String variable) {
        // Texto
        String sTexto = variable;
        // Texto que vamos a buscar
        String sTextoBuscado = "-";
        // Contador de ocurrencias 
        int contador = 0;

        while (sTexto.indexOf(sTextoBuscado) > -1) {
            sTexto = sTexto.substring(sTexto.indexOf(
                    sTextoBuscado) + sTextoBuscado.length(), sTexto.length());
            contador++;
        }

        return contador;

    }

}

