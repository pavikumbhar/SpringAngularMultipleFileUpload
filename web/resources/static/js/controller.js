app.factory(
        "transformRequestAsFormPost",
        function () {
            // I prepare the request data for the form post.
            function transformRequest(data, getHeaders) {
                var headers = getHeaders();
                headers[ "Content-type" ] = "application/x-www-form-urlencoded; charset=utf-8";
                return(serializeData(data));
            }
            // Return the factory value.
            return(transformRequest);
            // ---
            // PRVIATE METHODS.
            // ---
            // I serialize the given Object into a key-value pair string. This
            // method expects an object and will default to the toString() method.
            // --
            // NOTE: This is an atered version of the jQuery.param() method which
            // will serialize a data collection for Form posting.
            // --
            // https://github.com/jquery/jquery/blob/master/src/serialize.js#L45
            function serializeData(data) {
                // If this is not an object, defer to native stringification.
                if (!angular.isObject(data)) {
                    return((data == null) ? "" : data.toString());
                }
                var buffer = [];
                // Serialize each key in the object.
                for (var name in data) {
                    if (!data.hasOwnProperty(name)) {
                        continue;
                    }
                    var value = data[ name ];
                    buffer.push(
                            encodeURIComponent(name) +
                            "=" +
                            encodeURIComponent((value == null) ? "" : value)
                            );
                }
                // Serialize the buffer and clean it up for transportation.
                var source = buffer
                        .join("&")
                        .replace(/%20/g, "+")
                        ;
                return(source);
            }
        }
);
// -------------------------------------------------- //
// -------------------------------------------------- //
// I override the "expected" $sanitize service to simply allow the HTML to be
// output for the current demo.
// --
// NOTE: Do not use this version in production!! This is for development only.
app.value(
        "$sanitize",
        function (html) {
            return(html);
        }
);




app.controller('galleryController', function ($scope) {
    $scope.headingTitle = "Photo Gallery Items";
});

app.controller('contactusController', function ($scope) {
    $scope.headingTitle = "Contact Info";
});




//not used
app.directive('ngFileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.ngFileModel);
                var isMultiple = attrs.multiple;
                var modelSetter = model.assign;
                element.bind('change', function () {
                    var values = [];
                    angular.forEach(element[0].files, function (item) {
                        var value = {
                            // File Name 
                            name: item.name,
                            //File Size 
                            size: item.size,
                            //File URL to view 
                            url: URL.createObjectURL(item),
                            // File Input Value 
                            _file: item
                        };
                        values.push(value);
                    });
                    scope.$apply(function () {
                        if (isMultiple) {
                            modelSetter(scope, values);
                        } else {
                            modelSetter(scope, values[0]);
                        }
                    });
                });
            }
        };
    }]);


  
    
    app.controller("fileUploadController", ['$scope', '$http' , function ($scope, $http) {
             $scope.headingTitle = "File Upload";
         $scope.flist = [];
        $scope.personalDetails = [
            {
                'fname': 'pavi',
                'lname': 'kumbhar',
                'email': 'pavi@xyz.com',
                files: ""

            },
            {
                'fname': 'pravin',
                'lname': 'kumbhar',
                'email': 'pravin@xyz.com',
                files: ""
            },
            {
                'fname': 'mangesh',
                'lname': 'kumbhar',
                'email': 'mangesh@xyz.com',
                files: ""
            }];

        $scope.addNew = function (personalDetail) {
            $scope.personalDetails.push({
                'fname': "",
                'lname': "",
                'email': "",
                files: ""
            });
        };

        $scope.remove = function () {
            var newDataList = [];
            $scope.selectedAll = false;
            angular.forEach($scope.personalDetails, function (selected) {
                if (!selected.selected) {
                    newDataList.push(selected);
                }
            });
            $scope.personalDetails = newDataList;
        };

        $scope.checkAll = function () {
            if (!$scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach($scope.personalDetails, function (personalDetail) {
                personalDetail.selected = $scope.selectedAll;
            });
        };

        $scope.save = function (personalDetails) {

         
            $scope.files = [];

            $scope.Details = [];
            
           
           
            $scope.fileList = [];
           
            for (var i = 0; i < $scope.personalDetails.length; i++)
            {
                 var FinalImageList=[];
           
                $scope.fileList = personalDetails[i].files;

              
                for (var j = 0; j < $scope.fileList.length; j++)
                {
                    var FileList={};
                    
                   
                    var filesize=$scope.fileList[j].filesize;
                    var filetype=$scope.fileList[j].filetype;
                    var filename=$scope.fileList[j].filename;
                    var base64=$scope.fileList[j].base64;
                    
                    FileList.filesize=filesize;
                    FileList.filetype=filetype;
                    FileList.filename=filename;
                    FileList.base64=base64;
                    FinalImageList.push(FileList);
                   
                     
                      
                      
                }

                var eachRow =
                        {
                            "fname": personalDetails[i].fname,
                            "lname": personalDetails[i].lname,
                            "email": personalDetails[i].email,
                            "baseFiles":FinalImageList
                            
                        };

               
                $scope.Details.push(eachRow);
            }
           
            var uploadUrl = 'api/fileBase/';
            $http({
                method: 'POST',
                url: uploadUrl,
               // headers: {'Content-Type': "undefine},
                data:$scope.Details
                
            })
                    .success(function (data, status) {
                        alert("success");
                    })

        };
    }]);



app.factory('downloadService', ['$q', '$timeout', '$window',
        function($q, $timeout, $window) {
            return {
                download: function(name) {

                    var defer = $q.defer();

                    $timeout(function() {
                            $window.location = 'api/download?name=' + name;

                        }, 1000)
                        .then(function() {
                            defer.resolve('success');
                        }, function() {
                            defer.reject('error');
                        });
                    return defer.promise;
                }
            };
        }
    ]);
    
    
    
 
       app.controller('DownloadSampleCtrl', ['downloadService', function(downloadService) {
            this.download = function(fileName) {
                downloadService.download(fileName)
                    .then(function(success) {
                        console.log('success : ' + success);
                    }, function(error) {
                        console.log('error : ' + error);
                    });
            };
        }]);
    
    
    
    
   