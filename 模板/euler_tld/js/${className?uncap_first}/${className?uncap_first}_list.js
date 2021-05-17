define(["core_js/grid/SimpleListView",
    "core_js/grid/DataGridUtils",
    "core_js/context/ApplicationContext"], function (SimpleListView, DataGridUtils, ApplicationContext) {
    return SimpleListView.extend({
        title: "${table.comment!}列表",
        titlebar: true,
        toolbar: {
            items: [
                {
                    id: "add",
                    text: "添加",
                    onclick: function (e) {
                        const toolbarItemObj = e.target;
                        const toolbarObj = toolbarItemObj.getParent();
                        const ${className?uncap_first}ListView = toolbarObj.getParent();
                        $.window.showModalDialog("新增",
                            "#tld/bf/${className?uncap_first}/add",
                            null,
                            function () {
                                ${className?uncap_first}ListView.getDataGridObject().refresh();
                            },
                            null,
                            {
                                width: 700,
                                height: 400
                            });
                    }
                },
                {
                    id: "delete",
                    text: "删除",
                    onclick: function (e) {
                        const toolbarItemObj = e.target;
                        const toolbarObj = toolbarItemObj.getParent();
                        const ${className?uncap_first}ListView = toolbarObj.getParent();
                        const dataGridObject = ${className?uncap_first}ListView.getDataGridObject();
                        if (DataGridUtils.checkSelection(dataGridObject)) {
                            var checkedValues = DataGridUtils.getCheckedValues(dataGridObject);
                            $.window.confirm("确定要删除该记录吗？", {
                                yesHandle: function () {
                                    ApplicationContext.get("ropClient").post({
                                        methodName: "${className?uncap_first}.delete",
                                        methodVersion: "1.0",
                                        data: {ids: checkedValues},
                                        complete: function (compositeResponse) {
                                            if (compositeResponse.isSuccessful()) {
                                                $.window.showSucceedMsg("操作成功");
                                                dataGridObject.refresh();
                                            } else {
                                                $.window.showErrorMsg(compositeResponse.getMessage());
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }
                }
            ]
        },
        grid: {
            ajaxClient: ApplicationContext.get("ropClient"),
            methodName: "${className?uncap_first}.list.get",
            methodVersion: "1.0",
            primaryKey: "${id}",
            displayRowNumber: true,
            displayCheckBox: true,
            pageable: true,
            columns: [
                <#list table.columns as column>
                {field: "${column.columnName2}", title: "${column.columnComment}", width: "100px" <#if column.columnKey="1">, hidden: true</#if>}<#if column_has_next>,</#if>
                </#list>
            ],
            actions: [
                {
                    id: "update",
                    title: "修改",
                    iconSkin: SimpleListView.IconSkin.EDIT,
                    onclick: function () {
                        const id = this.getPrimaryKey();
                        if (!id) {
                            return;
                        }
                        const ${className?uncap_first} = this;
                        $.window.showModalDialog("修改",
                            "#tld/bf/${className?uncap_first}/update?${id}=" + id,
                            null,
                            function () {
                                ${className?uncap_first}.refresh();
                            },
                            null,
                            {
                                width: 700,
                                height: 400
                            });
                    }
                },
                {
                    id: "view",
                    title: "查看",
                    iconSkin: SimpleListView.IconSkin.VIEW,
                    onclick: function () {
                        const id = this.getPrimaryKey();
                        if (!id) {
                            return;
                        }
                        $.window.showModalDialog("查看",
                            "#tld/bf/${className?uncap_first}/view?${id}=" + id,
                            null,
                            null,
                            null,
                            {
                                width: 700,
                                height: 400
                            });
                    }
                }
            ]
        }
    });
});
