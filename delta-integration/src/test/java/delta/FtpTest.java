package delta;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class FtpTest {

    @Test
    public void putしてlistしてgetしてrmする() throws Exception {
        File tempFile = File.createTempFile("hoge", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            writer.write("テスト");
        }

        DefaultFtpSessionFactory sessionFactory = new DefaultFtpSessionFactory();
        sessionFactory.setHost("localhost");
        sessionFactory.setPort(21);
        sessionFactory.setUsername("bob");
        sessionFactory.setPassword("password");
        sessionFactory.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);

        FtpRemoteFileTemplate template = new FtpRemoteFileTemplate(sessionFactory);
        template.setRemoteDirectoryExpression(new LiteralExpression("/"));

        // put
        template.send(MessageBuilder.withPayload(tempFile).build());

        // list
        FTPFile[] list = template.list("/");
        assertThat(list)
                .extracting(FTPFile::getName)
                .contains(tempFile.getName());

        // get
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.get(tempFile.getName(), is -> IOUtils.copy(is, baos));
        assertThat(baos.toString()).isEqualTo("テスト");

        // rm
        template.remove(tempFile.getName());
        assertThat(template.list("/"))
                .extracting(FTPFile::getName)
                .doesNotContain(tempFile.getName());
    }
}
