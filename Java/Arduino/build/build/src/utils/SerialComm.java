package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public abstract class SerialComm implements SerialPortEventListener {
	public enum Status {
		DISCONNECTED, CONNECTED, UNKNOWN;
	}

	// Milliseconds to block while waiting for port open
	private static final int TIMEOUT = 2000;

	// Default bits per second for COM port
	private int baudRate;

	private BufferedReader input;
	private OutputStream output;

	private Status status = Status.UNKNOWN;
	private SerialPort comPort;

	private static final String PORT_NAME[] = { "/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM3", // Windows
	};

	public SerialComm() {
		this.baudRate = 9600;
	}

	public SerialComm(int baudRate) {
		this.baudRate = baudRate;
	}

	public void init() {
		CommPortIdentifier portId = null;
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier evalutePortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAME) {
				if (evalutePortId.getName().equals(portName)) {
					portId = evalutePortId;
				}
			}
		}
		if (portId == null) {
			status = Status.DISCONNECTED;
			return;
		}
		try {
			comPort = (SerialPort) portId.open(getClass().getName(), TIMEOUT);
			comPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			input = new BufferedReader(new InputStreamReader(comPort.getInputStream()));
			output = comPort.getOutputStream();
			comPort.addEventListener(this);
			comPort.notifyOnDataAvailable(true);

			status = Status.CONNECTED;
		} catch (Exception e) {
			System.err.println(e.toString());
		}

	}

	public synchronized void close() {
		try {
			input.close();
			output.close();
		} catch (Exception e) {
		} finally {
			if (comPort != null) {
				comPort.removeEventListener();
				comPort.close();
			}
		}

	}

	public SerialPort getSerialPort() {
		return comPort;
	}

	public Status getStatus() {
		return status;
	}

	public abstract void onReceive(String res);

	public void send(String req) throws IOException {
		output.write(req.getBytes());
		output.flush();
	}

	@Override
	public synchronized void serialEvent(SerialPortEvent ev) {
		if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				onReceive(input.readLine());
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

}
