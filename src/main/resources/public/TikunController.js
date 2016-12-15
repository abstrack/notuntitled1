var module = angular.module('myapp', []);

module.controller('TikunController', function ($scope, $http) {

    $scope.tikunim = [];
    $http.get('http://localhost:8080/tikunim').success(function(data) {
        $scope.tikunim=data;

    });
    $scope.delete = function (data) {
        $http.delete('http://localhost:8080/tikunim/'+data).success(   $http.get('http://localhost:8080/tikunim').success(function(data) {
            $scope.tikunim=data;
        }));

    }

    document.getElementById("formLink").onclick = function()
    {
        document.getElementById("box").style.display = "block";
        document.getElementById("box-inner").style.display = "block";
    }
    document.getElementById("box").onclick = function(e)
    {
        if(e.toElement == document.getElementById("box-inner"))
        {
            document.getElementById("box").style.display = "none";
            document.getElementById("box-inner").style.display = "none";
        }
    }

});
module.controller('myCtrl2', function($scope, $http) {
    $scope.list = [];
    $scope.text = 'hello';
    $scope.submit = function() {
        if ($scope.text) {
            $http.post('http://localhost:8080/tikunim/'+$scope.text).success(
                $http.get('http://localhost:8080/tikunim').success(function(data) {
                    $scope.tikunim=data;
                }));

        }
    };
});
/**
 * Created by mikhailrofel on 30/11/2016.
 */
