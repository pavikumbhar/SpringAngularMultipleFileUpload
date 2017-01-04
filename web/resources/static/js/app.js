var app = angular.module('app', ['ngRoute','ngResource','naif.base64']);
app.config(function($routeProvider){
    $routeProvider
        
        .when('/contactus',{
            templateUrl: 'contactus',
            controller: 'contactusController'
        })
        .when('/gallery',{
            templateUrl: 'gallery',
            controller: 'galleryController'
        })
               
        .when('/fileUpload',{
            templateUrl: 'fileUpload',
            controller: 'fileUploadController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

