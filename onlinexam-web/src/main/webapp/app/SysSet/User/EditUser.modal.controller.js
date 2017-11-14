(function() {
        'use strict';
        angular
            .module('webfullApp')
            .controller('EditUserModalCtrl', EditUserModalCtrl);

        EditUserModalCtrl.$inject = ['$scope','$uibModalInstance','$http','$filter','userInfo','UserService'];

        function EditUserModalCtrl($scope,$uibModalInstance,$http,$filter,userInfo,UserService) {
        	var $ctrl = this;

            $ctrl.selected = '2';

            $ctrl.editUser={
                uid:userInfo.uid,
                displayName:userInfo.displayName,
                email:userInfo.email,
                phone:userInfo.phone,
                companyName:userInfo.companyName
            };

            console.info(userInfo);


            $ctrl.ok = function() {
                $uibModalInstance.close();
            };

            $ctrl.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            };

            $ctrl.ChangeChart = function(){
                //alert($ctrl.selected);
            }

            $ctrl.saveUser = function(){
                UserService.saveUser.save({
                    uid:$ctrl.editUser.uid,
                    displayName:$ctrl.editUser.displayName,
                    email:$ctrl.editUser.email,
                    phone:$ctrl.editUser.phone,
                    companyName:$ctrl.editUser.companyName
                },success);

                function success(){
                    alert("修改成功！");
                }
            }
        }
})();
