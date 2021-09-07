package examplegroup;

import com.hivemq.embedded.EmbeddedHiveMQ;
import com.hivemq.embedded.EmbeddedHiveMQBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EmbeddedMQTT {
    public EmbeddedMQTT() throws IOException {

        Path currentWorkingDirectory = new File("").toPath()
                .toAbsolutePath();


        // prepare storage area
        Path basePath = currentWorkingDirectory.resolve("mqtt-embedded");
        Path dataPath = basePath.resolve("data");
        Path configPath = basePath.resolve("conf");
        Path extensionsPath = basePath.resolve("extensions");

        Files.createDirectories(dataPath);
        Files.createDirectories(configPath);
        Files.createDirectories(extensionsPath);

        Path configFile = configPath.resolve("config.xml");

        if (Files.notExists(configFile)) {
            // https://github.com/hivemq/hivemq-community-edition/blob/559c6730b83fc5e6f296d8fb8c409c876e218a9b/src/main/resources/config.xml
            Files.writeString(configFile, "<hivemq><listeners><tcp-listener><port>1883</port><bind-address>0.0.0.0</bind-address></tcp-listener></listeners><anonymous-usage-statistics><enabled>false</enabled></anonymous-usage-statistics></hivemq>");
        }



        System.out.println(">>>>>>>>");
        System.out.println("currentWorkingDirectory: " + currentWorkingDirectory);
        System.out.println("basePath: " + basePath);
        System.out.println("dataPath: " + dataPath);
        System.out.println("configPath: " + configPath);
        System.out.println("extensionsPath: " + extensionsPath);
        System.out.println("configFile: " + configFile);
        System.out.println("<<<<<<<<");

        EmbeddedHiveMQBuilder embeddedHiveMQBuilder = EmbeddedHiveMQ.builder()
                .withDataFolder(dataPath.toAbsolutePath())
                .withConfigurationFolder(configPath.toAbsolutePath())
                .withExtensionsFolder(extensionsPath.toAbsolutePath());
        try (final EmbeddedHiveMQ hiveMQ = embeddedHiveMQBuilder.build()) {
            hiveMQ.start().join();
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
