/**
 * Created by carl.li on 2017/2/22.
 */
(function () {
    cola(function (model) {
        model.describe("roles", {
            provider: {
                url: "/service/frame/role/",
                pageSize: 2,
                beforeSend: function (self, arg) {
                    var contain = model.get("contain");
                    if (cola.defaultAction.isNotEmpty(contain)) {
                        // 使用encodeURI() 为了解决GET下传递中文出现的乱码
                        arg.options.data.contain = encodeURI(contain);
                    }
                },
                complete: function () {
                    // 生成序号
                    var serialNo = 1;
                    model.get("roles").each(function(role){
                        role.set("serialNo",serialNo++);
                    });
                    if (!model.get("roleId")) {
                        model.set("roleId",model.get("roles").current.get("id"));
                    }
                }
            }
        });
        model.describe("users", {
            provider: {
                url: "/service/frame/user/",
                pageSize: 2,
                beforeSend: function (self, arg) {
                    var contain = model.get("contain");
                    if (cola.defaultAction.isNotEmpty(contain)) {
                        // 使用encodeURI() 为了解决GET下传递中文出现的乱码
                        arg.options.data.contain = encodeURI(contain);
                    }
                }
            }
        });
        model.describe("positions", {
            provider: {
                url: "/service/frame/position/",
                pageSize: 2,
                beforeSend: function (self, arg) {
                    var contain = model.get("contain");
                    if (cola.defaultAction.isNotEmpty(contain)) {
                        // 使用encodeURI() 为了解决GET下传递中文出现的乱码
                        arg.options.data.contain = encodeURI(contain);
                    }
                }
            }
        });
        model.describe("depts", {
            provider: {
                url: "service/frame/dept/depts"
            }
        });
        model.describe("groups", {
            provider: {
                url: "/service/frame/group/",
                pageSize: 2,
                beforeSend: function (self, arg) {
                    var contain = model.get("contain");
                    if (cola.defaultAction.isNotEmpty(contain)) {
                        // 使用encodeURI() 为了解决GET下传递中文出现的乱码
                        arg.options.data.contain = encodeURI(contain);
                    }
                }
            }
        });

        model.action({

        });

        model.widgetConfig({
            roleTable: {
                $type: "table",
                bind: "role in roles",
                showHeader: true,
                changeCurrentItem: true,
                highlightCurrentItem: true,
                currentPageOnly: true,
                columns: [{
                    caption: "序号",
                    bind: ".serialNo",
                    width: 25
                },{
                    caption: "角色名称",
                    bind: ".name"
                },{
                    caption: "角色描述",
                    bind: ".desc"
                }],
                itemClick: function (self, arg) {
                    // 拿到当前行id,根据id获取后台功能数据
                    //var roleId = self.get("currentItem").get("roleCode");
                }
            },
            userTable: {
                $type: "table",
                bind: "user in users",
                showHeader: true,
                currentPageOnly: true,
                highlightCurrentItem: true,
                columns: [
                    {
                        bind: ".username",
                        caption: "用户名",
                        visible: false // 是否可见
                    }, {
                        bind: ".cname",
                        caption: "用户"
                    }, {
                        caption: "操作",
                        template: "operation"
                    }
                ]
            },
            positionTable: {
                $type: "table",
                bind: "position in positions",
                showHeader: true,
                currentPageOnly: true,
                highlightCurrentItem: true,
                columns: [
                    {
                        bind: ".name",
                        caption: "岗位名称"
                    }, {
                        caption: "操作",
                        template: "operation"
                    }
                ]
            },
            deptTable: {
                $type: "table",
                bind: "dept in depts",
                showHeader: true,
                currentPageOnly: true,
                highlightCurrentItem: true,
                columns: [
                    {
                        bind: ".name",
                        caption: "部门"
                    }, {
                        caption: "操作",
                        template: "operation"
                    }
                ]
            },
            groupTable: {
                $type: "table",
                bind: "group in groups",
                showHeader: true,
                currentPageOnly: true,
                highlightCurrentItem: true,
                columns: [
                    {
                        bind: ".name",
                        caption: "群组"
                    }, {
                        caption: "操作",
                        template: "operation"
                    }
                ]
            }

        });
    });
}).call(this);