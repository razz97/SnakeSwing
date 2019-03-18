package snaketucom;

import java.awt.EventQueue;

import controller.AppController;

public class SnakeTucom {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			AppController.getInstance().bootstrap();
		});
	}
}
