package com.huahua.spit.qa.controller;
import java.util.Map;


import com.huahua.spit.qa.eureka.LabelEureka;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import huahua.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huahua.spit.qa.pojo.Problem;
import com.huahua.spit.qa.service.ProblemService;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	HttpServletRequest httpServletRequest;

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		/*Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
		if(null == claims){
			return new Result(false,StatusCode.AUTOROLES,"请先登录");
		}*/
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 * 最新问答
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/newlist/{labelid}/{page}/{size}")
	public Result newlist(@PathVariable String labelid,@PathVariable Integer page,@PathVariable Integer size){
		Page<Problem> problemPage= problemService.newlist(labelid, page, size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(problemPage.getTotalElements(),problemPage.getContent()));
	}

	/**
	 * 等待问答
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/waitlist/{labelid}/{page}/{size}")
	public Result waitlist(@PathVariable String labelid,@PathVariable Integer page,@PathVariable Integer size){
		Page<Problem> problemPage= problemService.waitlist(labelid, page, size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(problemPage.getTotalElements(),problemPage.getContent()));
	}

	@Autowired
	private LabelEureka labelEureka;

	/**
	 * 调用huahua-base
	 * @param labelId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/label/{labelId}")
	public Result queryById(@PathVariable("labelId") String labelId){
		return new Result(true,StatusCode.OK,"执行成功",labelEureka.queryById(labelId));
	}
}
