    <#list list as x>
          ${x_index +1}.${x} <#if x_has_next>,</#if>
           <#if x = "星期四"><#break></#if>
       </#list>