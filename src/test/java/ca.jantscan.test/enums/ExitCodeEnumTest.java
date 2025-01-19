package ca.jantscan.test.enums;

import ca.jantscan.constants.Constants;
import ca.jantscan.enums.ExitCodeEnum;
import org.junit.Assert;
import org.junit.Test;

public class ExitCodeEnumTest {

    @Test
    public void test() {
        Assert.assertTrue(ExitCodeEnum.FAIL.getCode() == Constants.EXIT_CODE_FAIL);
        Assert.assertTrue(ExitCodeEnum.SUCCESS.getCode() == Constants.EXIT_CODE_SUCCESS);
        Assert.assertTrue(ExitCodeEnum.FAIL.getMessage().equals(Constants.EXIT_CODE_FAIL_MESSAGE));
        Assert.assertTrue(ExitCodeEnum.SUCCESS.getMessage().equals(Constants.EXIT_CODE_SUCCESS_MESSAGE));
    }
}
