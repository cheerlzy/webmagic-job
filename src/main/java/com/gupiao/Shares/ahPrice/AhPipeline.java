package com.gupiao.Shares.ahPrice;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author zhuxing [zhuxing1@xiaoniu66.com, xing_08@126.com]
 */
public class AhPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<AhEntity> news = resultItems.get("data");
    }
}
