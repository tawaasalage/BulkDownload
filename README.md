# TAWSOFT

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

BulkDownload is a library project that allows bulk content downloads in a cleaner way.


### Installation

BulkDownload requires Android SDK v21+ to integrate.

Copy bulkuploads.aar file in to your project lib folder

```sh
$ implementation (name: 'bulkuploads', ext:'aar')
```

In your gradle file

```sh
repositories {
        flatDir {
            dirs 'libs'
        }
    }
```

### Usage

```sh
  ArrayList<FilesModel> files=new ArrayList<>();
  
  FilesModel filesModel=new FilesModel();
  filesModel.setSavename("newfilename.jpeg");
  filesModel.setSavePath("PATH ON YOUR STORAGE");
  filesModel.setUrl("https://images.pexels.com/photos/531880/pexels-photo-531880.jpeg");
  files.add(filesModel);
  
  //Need to pass Activity here in order to show Progressbar
  BulkDownloadHelper dImg=new BulkDownloadHelper(MainActivity.this);
        dImg.DownoloadFiles(files, new UploadStatusCallback() {
            @Override
            public void onCompleted() {
                Log.d("Download","Success");
            }

            @Override
            public void onError() {

            }
        });
```

