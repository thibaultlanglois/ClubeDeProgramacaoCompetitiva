import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		boolean fromFile = false;
		Scanner sc= fromFile ? new Scanner(new File("in.txt")) : new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int count = sc.nextInt();
		sc.nextLine();
		while (count-- > 0) {
			String[] input = sc.nextLine().split(" ");
			byte[] time = new byte[2];
			String from = null;
			String to = null;
			if (input[0].equals("noon")) {
				time[0] = 12;
				time[1] = 0;
				from = input[1];
				to = input[2];
			} else if (input[0].equals("midnight")) {
				time[0] = 0;
				time[1] = 0;
				from = input[1];
				to = input[2];
			} else {
				String[] timeParts = input[0].split(":");
				time[0] = Byte.parseByte(timeParts[0]);
				time[1] = Byte.parseByte(timeParts[1]);
				boolean pm = input[1].equals("p.m.");
				if (pm && time[0] != 12)
					time[0] += 12;
				if (!pm && time[0] == 12)
					time[0] = 0;
				from = input[2];
				to = input[3];
			}
			byte[] utc = toUTC(time, from);
			byte[] converted = fromUTC(utc, to);
			if (converted[0] * 60 + converted[1] == 0) {
				System.out.println("midnight");
			} else if (converted[0] * 60 + converted[1] == 12 * 60) {
				System.out.println("noon");
			} else {
				int totalMin = converted[0] * 60 + converted[1];
				int hours = (totalMin / 60) % 12;
				if (hours == 0)
					hours = 12;
				int minutes = totalMin % 60;
				String ampm = (totalMin < 12 * 60) ? "a.m." : "p.m.";
				System.out.printf("%d:%02d %s", hours, minutes, ampm);
				System.out.println();
			}
		}
	}

	private static byte[] toUTC(byte[] time, String timezone) {
		int minutos = time[0] * 60 + time[1];
		int offset = getOffset(timezone);
		int utc = (minutos - offset + 24 * 60) % (24 * 60);
		return new byte[] { (byte) (utc / 60), (byte) (utc % 60) };
	}

	private static byte[] fromUTC(byte[] time, String timezone) {
		int minutos = time[0] * 60 + time[1];
		int offset = getOffset(timezone);
		int local = (minutos + offset + 24 * 60) % (24 * 60);
		return new byte[] { (byte) (local / 60), (byte) (local % 60) };
	}

	private static int getOffset(String timezone) {
		if (timezone.equals("UTC") || timezone.equals("GMT") || timezone.equals("WET"))
			return 0;
		else if (timezone.equals("BST") || timezone.equals("IST") || timezone.equals("WEST") || timezone.equals("CET"))
			return 1 * 60;
		else if (timezone.equals("CEST") || timezone.equals("EET"))
			return 2 * 60;
		else if (timezone.equals("EEST") || timezone.equals("MSK"))
			return 3 * 60;
		else if (timezone.equals("MSD"))
			return 4 * 60;
		else if (timezone.equals("AST"))
			return -4 * 60;
		else if (timezone.equals("ADT"))
			return -3 * 60;
		else if (timezone.equals("NST"))
			return -3 * 60 - 30;
		else if (timezone.equals("NDT"))
			return -2 * 60 - 30;
		else if (timezone.equals("EST"))
			return -5 * 60;
		else if (timezone.equals("EDT"))
			return -4 * 60;
		else if (timezone.equals("CST"))
			return -6 * 60;
		else if (timezone.equals("CDT"))
			return -5 * 60;
		else if (timezone.equals("MST"))
			return -7 * 60;
		else if (timezone.equals("MDT"))
			return -6 * 60;
		else if (timezone.equals("PST"))
			return -8 * 60;
		else if (timezone.equals("PDT"))
			return -7 * 60;
		else if (timezone.equals("HST"))
			return -10 * 60;
		else if (timezone.equals("AKST"))
			return -9 * 60;
		else if (timezone.equals("AKDT"))
			return -8 * 60;
		else if (timezone.equals("AEST"))
			return 10 * 60;
		else if (timezone.equals("AEDT"))
			return 11 * 60;
		else if (timezone.equals("ACST"))
			return 9 * 60 + 30;
		else if (timezone.equals("ACDT"))
			return 10 * 60 + 30;
		else if (timezone.equals("AWST"))
			return 8 * 60;
		return 0;
	}
}
