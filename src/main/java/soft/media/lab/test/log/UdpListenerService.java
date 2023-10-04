package soft.media.lab.test.log;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class UdpListenerService {

    private final int listeningPort = 9999; // используем тот же порт, на который отправляем

    public UdpListenerService() {
        // Прослушиваем UDP-сообщения
        listenUdpMessages();
    }

    private void listenUdpMessages() {
        Mono.fromRunnable(() -> {
            try (DatagramSocket socket = new DatagramSocket(listeningPort, InetAddress.getByName("localhost"))) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (true) {
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Received UDP message: " + received);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).subscribeOn(Schedulers.boundedElastic()).subscribe();
    }
}
