package com.lijs.fizz.user.model.pager;

import com.lijs.fizz.common.web.pager.BasePage;
import com.lijs.fizz.core.model.vo.UserVO;
import lombok.Data;

import java.util.List;

/**
 * @author ljs
 * @date 2025-01-14
 * @description
 */
@Data
public class OrgPage extends BasePage<UserVO> {

    private String name;

    private List<Integer> status;

}
