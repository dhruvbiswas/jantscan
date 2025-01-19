package ca.jantscan.test;

import ca.jantscan.JAntScanMain;
import ca.jantscan.enums.ExitCodeEnum;
import org.junit.Assert;
import org.junit.Test;

public class JAntScanMainTest {

    @Test
    public void test_valid() {
        String fPath = this.getClass().getClassLoader().getResource("jantscan-1.0-SNAPSHOT.jar").getFile();

        ExitCodeEnum exitCodeEnum = ExitCodeEnum.FAIL;

        try {
            Assert.assertTrue(JAntScanMain.run(fPath).getCode() == ExitCodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test_bad_jar() {
        String fPath = this.getClass().getClassLoader().getResource("LICENSE.jar").getFile();

        try {
            Assert.assertTrue(JAntScanMain.run(fPath).getCode() == ExitCodeEnum.FAIL.getCode());
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

}
