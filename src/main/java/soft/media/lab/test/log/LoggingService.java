package soft.media.lab.test.log;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Service
public class LoggingService {

    private final DatagramSocket socket;
    private final InetAddress address;
    private final int port;

    private final Sinks.Many<LogEvent> sink;

    public LoggingService() throws Exception {
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName("localhost");
        this.port = 9999;

        this.sink = Sinks.many().multicast().onBackpressureBuffer();

        this.sink.asFlux()
                .doOnNext(this::sendLogEventThroughUDP)
                .subscribe();
    }

    public void log(String message) {
        sink.tryEmitNext(new LogEvent(message));
    }

    private void sendLogEventThroughUDP(LogEvent event) {
        String logMessage = event.getTimestamp() + ": " + event.getMessage();
        byte[] buffer = logMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
