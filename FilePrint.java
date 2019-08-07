package JavaCallFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javaDemo.Reports;
import javaDemo.Statistics;

@WebServlet("/FilePrint")
public class FilePrint extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
		request.setAttribute("test", "This is a test.");
		
		Map<Double,Integer> frequencyTable = new HashMap<Double, Integer>();
		
		frequencyTable.put((double)1,3);
		frequencyTable.put((double)2,6);
		frequencyTable.put((double)4,1);
		request.setAttribute("frequencytable", frequencyTable);
		request.setAttribute("keys", extractKeysAsJSArray(frequencyTable));
		request.setAttribute("values", extractValuesAsJSArray(frequencyTable));
		request.setAttribute("colors", generateColors(frequencyTable));
		request.getRequestDispatcher("/WEB-INF/PrintPage.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		Reports[] statement = (Reports[]) request.getSession().getAttribute("Final");
		Map<Double, Integer> frequencyTable = Statistics.FrequencyTable(statement);
		request.setAttribute("frequencytable", frequencyTable);
		request.setAttribute("keys", extractKeysAsJSArray(frequencyTable));
		request.setAttribute("values", extractValuesAsJSArray(frequencyTable));
		request.setAttribute("colors", generateColors(frequencyTable));
		request.getRequestDispatcher("/WEB-INF/PrintPage.jsp").forward(request, response);
	}
	
	/*
	 * Generates random unique color set for each bin in the pie or doughnut chart.
	 */
	public static String generateColors(Map<Double, Integer> frequencyTable) {
		double[] values = extractValues(frequencyTable);
		String[] colors = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			colors[i] = String.format("\"#%06X\"", (int)(Math.random() * Math.pow(16, 6)));
		}
		return Arrays.toString(colors);
	}
	
	public static String extractKeysAsJSArray(Map<Double, Integer> frequencyTable) {
		double[] keys = extractKeys(frequencyTable);
		String[] s = new String[keys.length];
		for (int i = 0; i < s.length; i++) {
			s[i] = "\'" + String.valueOf((int)keys[i]) + "\'";
		}
		return "" + Arrays.toString(s) + "";
	}
	
	public static double[] extractKeys(Map<Double, Integer> frequencyTable) {
		//Double[] doubleSet = (Double[])frequencyTable.keySet().toArray();
		
		Set<Double> result = (Set<Double>) frequencyTable.keySet();

		Object[] doubleSet = result.toArray();
		double[] stringSet = new double[doubleSet.length];
		for (int i = 0; i < stringSet.length; i++) {
			stringSet[i] = (Double) doubleSet[i];
		}
		return stringSet;
	}
	
	public static String extractValuesAsJSArray(Map<Double, Integer> frequencyTable) {
		double[] values = extractValues(frequencyTable);

		return "" + Arrays.toString(values) + "";
	}
	
	public static double[] extractValues(Map<Double, Integer> frequencyTable) {
		Set<Double> doubleSet = (Set<Double>)frequencyTable.keySet();
		double[] stringSet = new double[frequencyTable.size()];
		int i = 0;
		for (double d : doubleSet) {
			stringSet[i] = frequencyTable.get(d);
			i++;
		}
		return stringSet;
	}
}
