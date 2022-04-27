package org.jeecg.modules.platform.dataexchange.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.platform.dataexchange.entity.FxJobHis;
import org.jeecg.modules.platform.dataexchange.service.IFxJobHisService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 定时任务执行记录
 * @Author: jeecg-boot
 * @Date:   2022-04-20
 * @Version: V1.0
 */
@Api(tags="定时任务执行记录")
@RestController
@RequestMapping("/dataexchange/fxJobHis")
@Slf4j
public class FxJobHisController extends JeecgController<FxJobHis, IFxJobHisService> {
	@Autowired
	private IFxJobHisService fxJobHisService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fxJobHis
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "定时任务执行记录-分页列表查询")
	@ApiOperation(value="定时任务执行记录-分页列表查询", notes="定时任务执行记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FxJobHis>> queryPageList(FxJobHis fxJobHis,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FxJobHis> queryWrapper = QueryGenerator.initQueryWrapper(fxJobHis, req.getParameterMap());
		Page<FxJobHis> page = new Page<FxJobHis>(pageNo, pageSize);
		IPage<FxJobHis> pageList = fxJobHisService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param fxJobHis
	 * @return
	 */
	@AutoLog(value = "定时任务执行记录-添加")
	@ApiOperation(value="定时任务执行记录-添加", notes="定时任务执行记录-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FxJobHis fxJobHis) {
		fxJobHisService.save(fxJobHis);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fxJobHis
	 * @return
	 */
	@AutoLog(value = "定时任务执行记录-编辑")
	@ApiOperation(value="定时任务执行记录-编辑", notes="定时任务执行记录-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FxJobHis fxJobHis) {
		fxJobHisService.updateById(fxJobHis);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "定时任务执行记录-通过id删除")
	@ApiOperation(value="定时任务执行记录-通过id删除", notes="定时任务执行记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		fxJobHisService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "定时任务执行记录-批量删除")
	@ApiOperation(value="定时任务执行记录-批量删除", notes="定时任务执行记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fxJobHisService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "定时任务执行记录-通过id查询")
	@ApiOperation(value="定时任务执行记录-通过id查询", notes="定时任务执行记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FxJobHis> queryById(@RequestParam(name="id",required=true) String id) {
		FxJobHis fxJobHis = fxJobHisService.getById(id);
		if(fxJobHis==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(fxJobHis);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fxJobHis
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FxJobHis fxJobHis) {
        return super.exportXls(request, fxJobHis, FxJobHis.class, "定时任务执行记录");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FxJobHis.class);
    }

}
