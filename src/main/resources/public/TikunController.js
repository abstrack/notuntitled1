var module = angular.module('myapp', []);

module.controller('TikunController', function ($scope, $http) {
    $scope.tikunim = [];
    $http.get('http://localhost:8080/tikunim').success(function(data) {
        $scope.tikunim=data;
    });
});
/**
 * Created by mikhailrofel on 30/11/2016.
 */
