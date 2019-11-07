define(["core_js/form/FormView",
    "core_js/CommonConstant",
    "core_js/utils/ApplicationUtil",
    "foundation_js/utils/ApplicationFramework",
    "core_js/grid/SimpleListView",
    "core_js/context/ApplicationContext"], function (FormView, CommonConstant, ApplicationUtil, ApplicationFramework, SimpleListView, ApplicationContext) {
    return FormView.extend({
        initialize: function (options) {
            this._action = options["action"];
            this._initToolbar();
            options = $.extend(true, this._getOptions(options), options);
            this._super(options);
        },
        /**
         * 是否为详情页面
         * @returns {boolean}
         * @private
         */
        _isDetailView: function () {
            return this._action === "view";
        },
        /**
         * 是否为新增页面
         * @returns {boolean}
         * @private
         */
        _isAddView: function () {
            return this._action === "add";
        },
        /**
         * 是否为修改页面
         * @returns {boolean}
         * @private
         */
        _isUpdateView: function () {
            return this._action === "update";
        },
        _getMethodName: function () {
            if (this._isAddView()) {
                return "${className?uncap_first}.add";
            } else if (this._isUpdateView()) {
                return "${className?uncap_first}.update";
            }
            return "";
        },
        _initToolbar: function () {
            var btnItems = [], btn;
            if (!this._isDetailView()) {
                btn = $.extend({}, FormView.ToolBar.SAVE_AND_CLOSE);
                btnItems.push(btn);
            }

            btn = $.extend({}, FormView.ToolBar.CANCEL);
            btnItems.push(btn);
            this.toolbar = {
                items: btnItems
            };
            this._super();
        },
        _getOptions: function (options) {
            var result, that = this, ${className?uncap_first} = {};

            if (this._isUpdateView() || this._isDetailView()) {
                var ${id} = options["${id}"];
                ApplicationContext.get("ropClient").post({
                    methodName: "${className?uncap_first}.get",
                    methodVersion: "1.0",
                    data: {id: ${id}},
                    async: false,
                    complete: function (response) {
                        if (response.isSuccessful()) {
                            ${className?uncap_first} = response.getSuccessResponse();
                        } else {
                            $.window.showErrorMsg(response.getErrorResponse().getMessage());
                        }
                    }
                });
            }

            result = {
                ajaxClient: ApplicationContext.get("ropClient"),
                methodName: this._getMethodName(),
                methodVersion: "1.0",
                isFit: false,
                cols: 2,
                fields: [
                    <#list table.columns as column>
                    {
                        name: "${column.columnName2}",
                        caption: "${column.columnComment}",
                        <#if column.columnType="Date">
                        editorType: SimpleListView.EditorType.DATE_TIME_EDITOR,
                        displayFormat: "yyyy-MM-dd HH:mm:ss",
                        hiddenFormat: "yyyy-MM-dd HH:mm:ss",
                        </#if>
                        rules: {
                            maxlength: ${column.columnSize}
                        },
                        value: ${className?uncap_first}.${column.columnName2} || ""
                    }<#if column_has_next>,</#if>
                    </#list>
                ],
                onrender: function () {
                    if (that._isDetailView()) {
                        this.getFormEditor().setScene(FormView.Scene.VIEW)
                    }
                }
            };
            return result;
        }
    });
});
/*
    //${table.comment!}配置
    "bf/${className?uncap_first}/list": "${className?uncap_first}_list",
    "bf/${className?uncap_first}/:action": "${className?uncap_first}_form",

    //${table.comment!}配置
    ${className?uncap_first}_list: function (params) {
    this.show("js/views/bf/${className?uncap_first}/${className?uncap_first}_list.js", params);
    },
    ${className?uncap_first}_form: function (action, params) {
        var param = params || {};
        param.action = action;
        this.show("js/views/bf/${className?uncap_first}/${className?uncap_first}_form.js", param);
    },
*/