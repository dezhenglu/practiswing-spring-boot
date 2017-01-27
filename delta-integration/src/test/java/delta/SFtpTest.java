package delta;

import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class SFtpTest {

    @Test
    public void putしてlistしてgetしてrmする() throws Exception {
        File tempFile = File.createTempFile("hoge", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile.toPath())) {
            writer.write("テスト");
        }

        DefaultSftpSessionFactory sessionFactory = new DefaultSftpSessionFactory();
        sessionFactory.setHost("localhost");
        sessionFactory.setPort(22);
        sessionFactory.setUser("foo");
        sessionFactory.setPassword("pass");
        sessionFactory.setAllowUnknownKeys(true);
        CachingSessionFactory<ChannelSftp.LsEntry> cachingSessionFactory = new CachingSessionFactory<>(sessionFactory);

        SftpRemoteFileTemplate template = new SftpRemoteFileTemplate(cachingSessionFactory);
        // remoteDirectoryExpressionはsendの時だけ使われる
        template.setRemoteDirectoryExpression(new LiteralExpression("upload"));

        // put
        template.send(MessageBuilder.withPayload(tempFile).build());

        // list
        ChannelSftp.LsEntry[] list = template.list("upload");
        assertThat(list)
                .extracting(ChannelSftp.LsEntry::getFilename)
                .contains(tempFile.getName());

        // get
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.get("upload/" + tempFile.getName(), is -> IOUtils.copy(is, baos));
        assertThat(baos.toString()).isEqualTo("テスト");

        // rm
        template.remove("upload/" + tempFile.getName());
        assertThat(template.list("upload"))
                .extracting(ChannelSftp.LsEntry::getFilename)
                .doesNotContain(tempFile.getName());
    }
}
