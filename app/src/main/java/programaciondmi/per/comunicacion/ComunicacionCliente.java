package programaciondmi.per.comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


import programaciondmi.per.comunicacion.modelo.Instrumento;
import programaciondmi.per.comunicacion.modelo.NotaMusical;
import programaciondmi.per.comunicacion.modelo.exceptions.MalaNotaMusicalException;

public class ComunicacionCliente extends Thread {
	private Socket s;
	private boolean conectado;

	public ComunicacionCliente() {
		try {
			s = new Socket(InetAddress.getByName("172.30.179.136"), 5000);
			conectado = true;
			System.out.println("conectado");
			// enviar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (conectado) {
			// recibir();
			try {
				recibirObjeto();
			} catch (MalaNotaMusicalException e) {
				e.printStackTrace();
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recibirObjeto() throws MalaNotaMusicalException {
		ObjectInputStream entrada = null;
		try {
			entrada = new ObjectInputStream(s.getInputStream());
			Object mensaje = entrada.readObject();
			System.out.println("Se recibio: " + mensaje);

			if (mensaje instanceof Instrumento) {
				Instrumento i = (Instrumento) mensaje;

				// Crear notas musicales y enviarlas

				NotaMusical n = new NotaMusical(i, NotaMusical.DO, NotaMusical.BLANCA);
				enviarObjeto(n);

			}
			
		} catch (IOException e) {
			try {
				if (entrada != null) {
					entrada.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarObjeto(Object o) {
		ObjectOutputStream salida = null;

		try {
			salida = new ObjectOutputStream(s.getOutputStream());
			salida.writeObject(o);
			System.out.println("Se envió el mensaje: " + o);
		} catch (IOException e) {
			try {
				if (salida != null) {
					salida.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		}
	}
}