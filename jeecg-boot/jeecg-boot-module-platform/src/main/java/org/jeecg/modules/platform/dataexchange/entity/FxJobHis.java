package org.jeecg.modules.platform.dataexchange.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 定时任务执行记录
 * @Author: jeecg-boot
 * @Date:   2022-04-20
 * @Version: V1.0
 */
@Data
@TableName("fx_job_his")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="fx_job_his对象", description="定时任务执行记录")
public class FxJobHis implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
    private java.lang.String jobName;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15)
    @ApiModelProperty(value = "执行时间")
    private java.util.Date executionTime;
	/**返回数据*/
	@Excel(name = "返回数据", width = 15)
    @ApiModelProperty(value = "返回数据")
    private java.lang.String returnData;
	/**耗时*/
	@Excel(name = "耗时", width = 15)
    @ApiModelProperty(value = "耗时")
    private java.lang.Integer costTime;
	/**执行状态*/
	@Excel(name = "执行状态", width = 15, dicCode = "http_status")
	@Dict(dicCode = "http_status")
    @ApiModelProperty(value = "执行状态")
    private java.lang.Integer status;
}
