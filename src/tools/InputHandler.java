/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class InputHandler {

    public static Scanner sc = new Scanner(System.in);

    public static int getInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getInt(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (lowerBound > upperBound) {
                    upperBound = upperBound + lowerBound - (lowerBound = upperBound);
                }
                if (inputNum < lowerBound || inputNum > upperBound) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                String msg = String.format("Must be in [%d,%d]", lowerBound, upperBound);
                System.out.println(msg);
            }
        }
    }

    public static int getPositiveInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0!");
            }
        }
    }

    public static double getPositiveReal(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                double inputNum = Double.parseDouble(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0");
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (inputStr.isBlank()) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        return sc.nextLine();
    }

    public static String getString(String inputMsg, String errorMsg, String regex) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(regex)) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg, int minLength, int maxLength) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                int strLen = inputStr.length();
                if (strLen < minLength || strLen > maxLength) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static boolean getBoolean(String inputMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                boolean inputBool = Boolean.parseBoolean(sc.nextLine());
                return inputBool;
            } catch (Exception e) {
                System.out.println("True or False!!");
            }
        }
    }

    public static String getDate(String inputMsg, String dateFormat) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (checkValidDate(inputStr, dateFormat)) {
                    return inputStr;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: " + dateFormat);
            }
        }
    }

    public static boolean checkValidDate(String date, String dateFormat) {
        try {
            if (!isValidDateFormat(date, dateFormat)) {
                throw new IllegalArgumentException("Invalid date format");
            }
            int day , month , year ;
            String[] dateParts = date.split("[- /.]");
            if (dateFormat.equals("mm/dd/yyyy")) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
            } else {
                day = Integer.parseInt(dateParts[1]);
                month = Integer.parseInt(dateParts[0]);
            }
            year = Integer.parseInt(dateParts[2]);
            
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Invalid month value");
            }
            return true ;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static final String DDMMYYYY_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private static final String MMDDYYYY_REGEX = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
    
    private static boolean isValidDateFormat(String date, String format) {
        String regex = (format.equals("dd/mm/yyyy")) ? DDMMYYYY_REGEX : MMDDYYYY_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
