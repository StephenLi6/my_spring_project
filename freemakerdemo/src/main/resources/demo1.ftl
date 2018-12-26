<html>
<head>
    <meta charset="UTF-8">
    <title>FreeMker入門小Demo</title>
</head>
<#include "head.ftl">
<body>
<#--我是一個注釋，我不會有任何輸出-->
${name},你好。${message}
<#assign linkman="劉先生">
聯繫人：${linkman}<br/>
<#assign info={"mobile":"13301231212","address":"北京市天安門"}>
電話：${info.mobile},地址：${info.address}<br/>

<#if success=true>
你已通過實名認證
<#else>
你未通過實名認證
</#if>
<br>
<#list goodsList as goods>
    ${goods_index+1}商品名稱：${goods.name} 價格：${goods.price}<br>
</#list>
<br>
共${goodsList?size}條記錄
<br>

<#list alList as list>
    ${list}<br>
</#list>
<br>

<#assign text="{'bank':'工商銀行','account':'101010101010'}">
<#assign data=text?eval >
開戶行：${data.bank}賬號：${data.account}
<br>

當前日期：${today?date}<br>
當前時間：${today?time}<br>
當前日期+時間：${today?datetime}<br>
日期格式化：${today?string("yyyy年MM月")}
<br>

${point?c}

<br>
<#if aaa??>
    aaa變量存在
<#else >
    aaa變量不存在
</#if>
<br>
${aaa!'---'}

</body>
</html>