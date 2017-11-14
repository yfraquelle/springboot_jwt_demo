(function() {
    'use strict';

    angular
        .module('webfullApp')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$uibModal', 'UserService'];

    function UserController($scope, $uibModal, UserService) {
        var vm = this;
        vm.pageChanged = pageChanged;
        vm.search = search;
        vm.open = open;
        vm.all = all;
        vm.chk = chk;
        vm.deleteUser = deleteUser;
        vm.deleteBatchUser = deleteBatchUser;
        vm.resetPassword = resetPassword;
        vm.openModalBatchUser = openModalBatchUser;
        vm.table = {
            userArray: []
        };
        vm.pagination = {
            maxSize: 5,
            bigCurrentPage: 1,
            bigTotalItems: ''
        };
        vm.check = {
            allArray: [],
            selectedArray: []
        };
        vm.searchCondition = {};

        getUserPage();

        function resetPassword() {
            var mymessage = confirm("确定要充值密码？");
            if (mymessage == true) {
                UserService.resetPassword.get({ uid: vm.check.selectedArray }, success);

                function success() {
                    alert("重置为初始密码123456");
                }
            }

        }

        function openModalBatchUser() {
            var mymessage = confirm("确定要删除嘛？");
            if (mymessage == true) {
                deleteBatchUser();
            }
        }

        function deleteBatchUser() {
            UserService.deleteBatchUser.delete({ uid: vm.check.selectedArray }, success);

            function success() {
                alert("hi");
            }
        }

        function open(data) {
            var modalInstance = $uibModal.open({
                //     animation: $scope.animationsEnabled, //打开时的动画开关
                templateUrl: 'app/SysSet/User/EditUser.modal.html', //模态框的页面内容,这里的url是可以自己定义的,也就意味着什么都可以写
                controller: 'EditUserModalCtrl', //这是模态框的控制器,是用来控制模态框的
                controllerAs: '$ctrl',
                size: 'lg', //模态框的大小尺寸
                scope: $scope,
                keyboard: false,
                resolve: { //这是一个入参,这个很重要,它可以把主控制器中的参数传到模态框控制器中
                    userInfo: function() { //items是一个回调函数
                        return data;
                    }
                }
            });
            // modalInstance.result.then(function() {
            //    // $ctrl.selected = selectedItem;
            //    console.info("hi_alert!");

            // }, function() {
            //     console.info('modal-component dismissed at: ' + new Date());
            // });
        }

        function deleteUser(uid) {
            var mymessage = confirm("确定要删除该用户？");
            if (mymessage == true) {
                UserService.deleteUser.delete({ uid: uid }, success);

                function success() {
                    alert("删除成功！");
                }
            }

        }

        function search() {
            vm.pagination.bigCurrentPage = 1;
            getUserPage();
        }

        function all(checkAll) {
            if (checkAll == true) {
                vm.check.selectedArray = vm.check.allArray;
            } else {
                vm.check.selectedArray = [];
            }
            console.info(vm.check.selectedArray);
        }

        function chk(checkOne, uid) {
            if (checkOne == true) {
                vm.check.selectedArray.push(uid);
            } else {
                vm.check.selectedArray.remove(uid);
            }
            console.info(vm.check.selectedArray);
        }

        function pageChanged() {
            getUserPage();
        };

        function getUserPage() {
            UserService.getUserPage.get({
                page: vm.pagination.bigCurrentPage - 1,
                size: 10,
                phone: vm.searchCondition.phone,
                uid: vm.searchCondition.userName,
                status: 1
            }, function(response) {
                vm.table.userArray = response.content;
                vm.pagination.bigTotalItems = response.totalElements;
                initCheckArray();
                console.info(vm.check.allArray);
            });
        }

        function initCheckArray() {
            vm.check.allArray = [];
            for (var i = 0; i < vm.table.userArray.length; i++) {
                vm.check.allArray.push(vm.table.userArray[i].uid);
            }
        }

        Array.prototype.indexOf = function(val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };

        Array.prototype.remove = function(val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };

    }

})();
