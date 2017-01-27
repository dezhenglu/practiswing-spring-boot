package delta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeltaFtpTest {

    @Autowired
    FtpRemoteFileTemplate template;

    @Test
    public void アップロードする() {
        template.send(
                MessageBuilder
                        .withPayload("送りたい情報")
                        // Fileなら DefaultFileNameGenerator が File#getName() を返す
                        // 文字列を送ると {id}.msg になる
                        // "file_name"ヘッダに設定しておけばこれが使われる
                        .setHeader(FileHeaders.FILENAME, "test.txt")
                        .build(),
                FileExistsMode.REPLACE);
    }
}
