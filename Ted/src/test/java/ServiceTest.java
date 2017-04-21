import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.huan.ted.account.dto.Role;
import com.huan.ted.account.dto.User;
import com.huan.ted.account.mapper.RoleMapper;
import com.huan.ted.account.mapper.UserRoleMapper;
import com.huan.ted.account.service.impl.RoleServiceImpl;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.impl.RequestHelper;
import com.huan.ted.core.impl.ServiceRequest;

/**
 * Created by hailor on 16/9/21.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    @Mock
    private RoleMapper roleMapper;

    @Mock
    private UserRoleMapper userRoleMapper;

  

    @InjectMocks
    private RoleServiceImpl roleService;

    IRequest request;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        //roleService = new RoleServiceImpl();
        //roleService.setRoleMapper(roleMapper);
        //roleService.setRoleFunctionService(roleFunctionService);
        //roleService.setUserRoleMapper(userRoleMapper);
        request= new ServiceRequest();
        request.setLocale("zh_CN");
        RequestHelper.setCurrentRequest(request);
    }

    @Test
    public void test(){
        List<Role> someList = new ArrayList<Role>();
        Role firstRole = new Role();
        firstRole.setRoleId(1L);
        someList.add(firstRole);
        when(roleMapper.selectRoleNotUserRoles(anyLong())).thenReturn(someList);
        List<Role> roles = roleService.selectRoleNotUserRoles(request,new User(),1,0);
        assertEquals((long)roles.get(0).getRoleId(),1L);
        verify(roleMapper, times(1)).selectRoleNotUserRoles(anyLong());
    }
}
