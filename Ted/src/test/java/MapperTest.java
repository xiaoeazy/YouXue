import com.huan.ted.account.dto.Role;
import com.huan.ted.account.mapper.RoleMapper;
import com.huan.ted.core.IRequest;
import com.huan.ted.core.impl.RequestHelper;
import com.huan.ted.core.impl.ServiceRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hailor on 16/9/21.
 */
@ContextConfiguration(locations = {"classpath:/spring/test/applicationContext.xml","classpath:/spring/test/applicationContext-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
public class MapperTest {
    @Autowired
    RoleMapper roleMapper;
    IRequest request;
    Role role;

    @Before
    public void setUp(){
        request= new ServiceRequest();
        request.setLocale("zh_CN");
        RequestHelper.setCurrentRequest(request);
        role = new Role();
        role.setRoleName("test");
        role.setRoleCode("test");
    }

    @Test
    @Rollback(false) 
    public void testprofile() throws Exception {
        roleMapper.insert(role);
        role.setRoleId(null);

        List<Role> roles= roleMapper.select(role);
        assertNotNull(roles);
        assertEquals(1,roles.size());
        assertEquals("test",roles.get(0).getRoleCode());

    }

    @After
    public  void tearDown(){

    }
}
