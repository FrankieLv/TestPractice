package com.frankie.mockito;

import com.frankie.UserDO;
import com.frankie.UserFeatureDO;
import com.frankie.UserServiceMapper;
import com.frankie.UserVO;
import com.frankie.impl.UserFeatureService;
import com.frankie.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private UserService mockUserService;

    @Spy
    private UserService spyUserService;

    @Mock
    private List<String> mockList;

    @InjectMocks
    @Spy
    private UserService userService;

    @Mock
    private UserFeatureService  userFeatureService;

    @Mock
    private UserServiceMapper userServiceMapper;

    @Test
    void shouldReturnUsers(){
        System.out.println("mockUserService is mock: " + Mockito.mockingDetails(mockUserService).isMock());
        System.out.println("mockUserService is spy: " + Mockito.mockingDetails(mockUserService).isSpy());
        System.out.println("spyUserService is mock: " + Mockito.mockingDetails(spyUserService).isMock());
        System.out.println("spyUserService is spy: " + Mockito.mockingDetails(spyUserService).isSpy());
    }

    /**
     * Mock对象无打桩时，直接返回mock对象的默认值
     * 默认值(int = 0), null(object)， 空集合等
     */
    @Test
    void shouldUpdateUser1(){
        System.out.println(mockUserService.modifyUser(new UserVO()));
    }

    /**
     * 参数对象必须保持一致，才可走打桩行为
     */
    @Test
    void shouldUpdateUser2(){
        UserVO userVO1 = new UserVO();
        userVO1.setUserName("test1");
        userVO1.setPhone("123456789");
        when(mockUserService.modifyUser(userVO1)).thenReturn(100);
        System.out.println(mockUserService.modifyUser(userVO1));

        UserVO userVO2 = new UserVO();
        userVO2.setUserName("test2");
        userVO2.setPhone("456789");
        System.out.println(mockUserService.modifyUser(userVO2));
    }

    /**
     * ArgumentMatchers.any(XXX.class)
     */
    @Test
    void shouldUpdateUser3(){

        //given
        when(mockUserService.modifyUser(ArgumentMatchers.any(UserVO.class))).thenReturn(100);

        //when
        UserVO userVO1 = new UserVO();
        userVO1.setUserName("test1");
        userVO1.setPhone("123456789");
        System.out.println(mockUserService.modifyUser(userVO1));

        UserVO userVO2 = new UserVO();
        userVO2.setUserName("test2");
        userVO2.setPhone("456789");
        System.out.println(mockUserService.modifyUser(userVO2));

        //then
    }

    @Test
    void testUserService4() throws InvocationTargetException, IllegalAccessException {
        //given
        Long userId = 1L;
        mockUserService.selectById(userId);
        //when
        //then
        verify(mockUserService, times(1)).selectById(ArgumentMatchers.anyLong());
    }

    @Test
    void testUserService5() {
        // 方法一
        UserVO userVO1 = new UserVO();
        doReturn(456).when(mockUserService).modifyUser(userVO1);
        assertEquals(456, mockUserService.modifyUser(userVO1));

        //spyUserService 只能使用doReturn()方式
        UserVO userVO2 = new UserVO();
        doReturn(456).when(spyUserService).modifyUser(userVO2);
        assertEquals(456, spyUserService.modifyUser(userVO2));

        // 方法二
        when(mockUserService.modifyUser(ArgumentMatchers.any(UserVO.class))).thenReturn(456);
        assertEquals(456, mockUserService.modifyUser(userVO1));
    }

    /**
     * void 返回值方法插桩
     */
    @Test
    void testUserService6(){
        //given
        doNothing().when(mockList).clear();
        //when
        mockList.clear();
        //then
        verify(mockList, times(1)).clear();
    }

    @Test
    void testUserService7(){
        // 方法一
        doThrow(RuntimeException.class).when(mockList).clear();
        try{
            mockList.clear();
            fail();
        }catch(Exception e){{
            assertTrue(e instanceof RuntimeException);
        }}

        // 方法二
        when(mockList.get(anyInt())).thenThrow(RuntimeException.class);
        try{
            mockList.get(4);
            fail();
        }catch(Exception e){{
            assertTrue(e instanceof RuntimeException);
        }}
    }

    /**
     * 第一次调用返回1， 第二次返回2， 第三次返回3
     */
    @Test
    void testUserService8(){
        //given
//        when(mockList.size()).thenReturn(1)
//                .thenReturn(2)
//                .thenReturn(3);
        when(mockList.size()).thenReturn(1, 2, 3);

        //when, then
        assertEquals(1, mockList.size());
        assertEquals(2, mockList.size());
        assertEquals(3, mockList.size());
    }

    @Test
    void testUserService9(){
        when(mockList.get(anyInt())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return String.valueOf(invocationOnMock.getArgument(0, Integer.class) * 100);
            }
        });

        String result = mockList.get(3);
        assertEquals("300", result);
    }

    @Test
    void testUserService10(){
        //mock对象调用真实方法
        //given
        when(mockUserService.modifyUser(any(UserVO.class))).thenCallRealMethod();
        //when
        int result = mockUserService.modifyUser(new UserVO());
        //then
        assertEquals(123, result);

        //spy对象默认调用真实方法
        assertEquals(123, spyUserService.modifyUser(new UserVO()));
    }

    @Test
    void testUserService11(){
        mockList.add("one");

        // 验证调用一次，且参数必须时"one", 否则验证失败
        verify(mockList).add("one");
        // 等价于上面
        verify(mockList, times(1)).add("one");

        //验证没有调用的两种方式
        verify(mockList, times(0)).add("two");
        verify(mockList, never()).add("two");

        //验证最好或者最多调用了多少次
        verify(mockList, atLeast(1)).add("one");
        verify(mockList, atMost(1)).add("one");
    }

    @Test
    void testUserService12(){
        //given
        List<UserFeatureDO> userFeatureDOList = new ArrayList<>();
        userFeatureDOList.add(new UserFeatureDO(Long.valueOf(1), "feature 1"));
        when(userFeatureService.selectByUserId(anyLong())).thenReturn(userFeatureDOList);

        //when, then
        assertEquals(1, userFeatureService.selectByUserId(Long.valueOf(1)).size());
    }


    // 实战部分 测试UserService.selectById() 方法

    @Test
    void userServiceShouldReturnNullWhenNoUserFound() throws InvocationTargetException, IllegalAccessException {
        //given
        when(userServiceMapper.getById(anyLong())).thenReturn(null);
        //when then
        assertNull(userService.selectById(anyLong()));
    }

    @Test
    void userServiceShouldReturnUserVOWithoutFeature() throws InvocationTargetException, IllegalAccessException {
        //given
        when(userServiceMapper.getById(anyLong())).thenReturn(new UserDO("Frankie", "123456"));
        when(userFeatureService.selectByUserId(anyLong())).thenReturn(null);
        //when then
        assertEquals("Frankie", userService.selectById(Long.valueOf(1)).getUserName());
        assertNull(userService.selectById(Long.valueOf(2)).getFeatureValues());
    }

    @Test
    void userServiceShouldReturnUserVOWithFeature() throws InvocationTargetException, IllegalAccessException {
        //given
        when(userServiceMapper.getById(anyLong())).thenReturn(new UserDO("Frankie", "123456"));

        List<UserFeatureDO> userFeatureDOList =  new ArrayList<UserFeatureDO>();
        UserFeatureDO userFeature = new UserFeatureDO(Long.valueOf(1), "feature");
        userFeatureDOList.add(userFeature);
        when(userFeatureService.selectByUserId(anyLong())).thenReturn(userFeatureDOList);

        //when then
        assertEquals(1, userService.selectById(Long.valueOf(2)).getFeatureValues().size());

    }
}
