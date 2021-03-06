package exam.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.hnzxl.exam.project.dto.ExamCacheInfo;
import cn.hnzxl.exam.project.service.UserQuestionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml"})
public class UserQuestionTest{
	private UserQuestionService userQuestionService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Test
	public void popList(){
		try{
			stringRedisTemplate.multi();
			String leftPop = stringRedisTemplate.opsForList().rightPop("系统信息:系统启动时间");
			System.out.println(leftPop);
			System.out.println(1/0);
			stringRedisTemplate.exec();
		}catch (Exception e) {
			stringRedisTemplate.discard();;
		}
	}
	
	@Test
	public void pushList(){
		long l1 = System.currentTimeMillis();
		try{
			ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
			stringRedisTemplate.multi();
			for (int i = 0; i < 10; i++) {
				opsForList.leftPush("系统信息:提交的题目","[INFO ][org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker.postProcessAfterInitialization(PostProcessorRegistrationDelegate.java:325)]Bean 'transactionAdvice' of type [class org.springframework.transaction.interceptor.TransactionInterceptor] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)[INFO ][org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker.postProcessAfterInitialization(PostProcessorRegistrationDelegate.java:325)]Bean 'transactionAdvice' of type [class org.springframework.transaction.interceptor.TransactionInterceptor] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)");
			}
			//System.out.println(1/0);
			stringRedisTemplate.exec();
		}catch (Exception e) {
			stringRedisTemplate.discard();
		}
		System.out.println(stringRedisTemplate.opsForList().size("系统信息:提交的题目")+"结束："+(System.currentTimeMillis()-l1));
	}
	
	@Test
	public void jsonTest(){
		String rightPop = stringRedisTemplate.opsForList().leftPop("exam:2019:examInfo");
		Object parse = JSON.parse(rightPop);
		System.out.println(parse);
	}
	/**
	 * 获取数据用的
	 */
	@Test
	public void jsonSDF(){
		ExamCacheInfo info = (ExamCacheInfo) redisTemplate.opsForList().rightPop("exam:2019:info");
		Object object = redisTemplate.opsForHash().get(info.getDataKey(), info.getUserId());
		redisTemplate.opsForHash().delete(info.getDataKey(), info.getUserId());
		System.out.println(object);
	}
}
