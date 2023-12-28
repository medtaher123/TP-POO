
package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class TableLayout {
    //TODO add to doc: explain TableLayout class
    //TODO doc: the biggest challenge was to support ANSI escape codes, since they are invisible characters that affect the length of the string

    private static final String CORNER_CHAR = ConsoleColors.getColoredString("+", "", true, false);//ConsoleColors.PURPLE_BACKGROUND) ; // "-" for no corners
    private static final String HORIZONTAL_LINE_CHAR = ConsoleColors.getColoredString("-", "", true, false);
    private static final String VERTICAL_LINE_CHAR = ConsoleColors.getColoredString("|", "", true, false);
    public static final List<String> ROW_SEPARATOR = null; //could have used null directly but this is more readable

    private final List<List<String>> tableData;
    private List<Integer> columnWidths;
    private final List<Boolean> leftPad = new ArrayList<>();


    public TableLayout() {
        this.tableData = new ArrayList<>();
    }

    public TableLayout(List<List<String>> tableData, List<Boolean> leftPad) {
        this.tableData = tableData;
        this.leftPad.addAll(leftPad);
    }

    public TableLayout(List<List<String>> tableData) {
        this.tableData = tableData;
        for (int i = 0; i < tableData.get(0).size(); i++) {
            leftPad.add(false);
        }
    }


    public void displayTable() {
        columnWidths = calculateColumnWidths();
        displayHorizontalLine();
        displayHeader(tableData.get(0)); // Display header row
        displayHorizontalLine();

        for (int i = 1; i < tableData.size(); i++) {
            displayRow(tableData.get(i));
        }

        displayHorizontalLine();
    }
    private void displayHeader(List<String> rowData) {
        System.out.print(VERTICAL_LINE_CHAR);
        for (int i = 0; i < rowData.size(); i++) {
            System.out.print(" " + ConsoleColors.getColoredString(pad(rowData.get(i), columnWidths.get(i), false),ConsoleColors.DEFAULT_BOLD) + " " + VERTICAL_LINE_CHAR);
        }
        System.out.println();
    }
    private void displayRow(List<String> rowData) {
        if (rowData == ROW_SEPARATOR) {
            displayHorizontalLine();
            return;
        }
        System.out.print(VERTICAL_LINE_CHAR);
        for (int i = 0; i < rowData.size(); i++) {
            System.out.print(" " + pad(rowData.get(i), columnWidths.get(i), leftPad.get(i)) + " " + VERTICAL_LINE_CHAR);
        }
        System.out.println();
    }

    private void displayHorizontalLine() {
        System.out.print(CORNER_CHAR);
        for (Integer width : columnWidths) {
            for (int i = 0; i < width + 2; i++) {
                System.out.print(HORIZONTAL_LINE_CHAR);
            }
            System.out.print(CORNER_CHAR);
        }
        System.out.println();
    }

    private List<Integer> calculateColumnWidths() {
        List<Integer> widths = new ArrayList<>();

        for (List<String> row : tableData) {
            if (row == ROW_SEPARATOR) continue;
            for (int i = 0; i < row.size(); i++) {
                int visibleLength = getVisibleLength(row.get(i));
                if (widths.size() <= i) {
                    widths.add(visibleLength);
                } else if (visibleLength > widths.get(i)) {
                    widths.set(i, visibleLength);
                }
            }
        }

        return widths;
    }

    private int getVisibleLength(String text) {
        /*Pattern pattern = Pattern.compile("\u001B\\[[;\\d]*m");
        Matcher matcher = pattern.matcher(text);*/
        return text.replaceAll("\u001B\\[[;\\d]*m", "").length();// - countMatches(matcher);
    }

    private int countMatches(Matcher matcher) {
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private String pad(String s, int n, boolean padLeft) {
        int visibleLength = s.length() - getVisibleLength(s);
        int totalLength = n + visibleLength;
        return String.format("%" + (padLeft ? "" : "-") + totalLength + "s", s);
    }


    public void setHeaders(String... headers) {
        tableData.clear();
        tableData.add(Arrays.asList(headers));
        leftPad.clear();
        for (int i = 0; i < headers.length; i++) {
            leftPad.add(false);
        }
    }

    public void setLeftPad(int index, boolean value) {
        leftPad.set(index, value);
        leftPad.set(index, value);
    }

    public void addRow(String... row) {
        tableData.add(Arrays.asList(row));
    }

    public void addRowSeparator() {
        tableData.add(ROW_SEPARATOR);
    }
}
