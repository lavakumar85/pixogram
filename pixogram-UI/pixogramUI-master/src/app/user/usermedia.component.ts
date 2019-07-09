import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { ImageService } from './image.service';
import { Router } from '@angular/router';

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  id: string;
  filename: string;
  caption: string;
  likes: string;
}

@Component({
  selector: 'app-usermedia',
  templateUrl: './usermedia.component.html',
  styleUrls: ['/../app.component.css']
})


export class UserMediaComponent implements OnInit {
  title = 'UserMedia';

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.getItem("jwtToken"),
  });

  imageMetadataUrl = 'http://localhost:8060/api/imagemetadata/{userId}'
  downloadImageUrl = 'http://localhost:8060/api/downloadFile/'

  tiles: Tile[] = [];
  imagesLoading: boolean;
  images: any[];
  retrievalFinished: boolean = false;

  constructor(private http: HttpClient, private imageService: ImageService, private router: Router) { }

  ngOnInit() {
    let params = new HttpParams();
    params = params.append('userId', localStorage.getItem('userId'));

    this.images = [];

    this.http.get<any>(this.imageMetadataUrl, { headers: this.headers, params }).toPromise().then(data => {
      var metadataindex = 0;
      while (data[metadataindex] != null) {
        this.getImage(data[metadataindex]);
        metadataindex++;
      }
    }
      ,
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          console.log('Server-side error occured.');
        }
      }
    )
    this.retrievalFinished = true;
  }

  getImage(imagedata) {
    this.imagesLoading = true;
    this.imageService.getImage(this.downloadImageUrl + imagedata.filename).toPromise().then(data => {
      let reader = new FileReader();
      reader.addEventListener("load", () => {
        this.images.push(reader.result);
        this.tiles.push({
          id: imagedata.id,
          filename: imagedata.filename,
          caption: imagedata.caption,
          likes: imagedata.likes,
          cols: 1,
          rows: 1,
          color: 'white' });
      }, false);
      if (data) {
        reader.readAsDataURL(data);
      }
      this.imagesLoading = false;
    }, error => {
      this.imagesLoading = false;
      console.log(error);
    }
    )
  }

  getRetrievalFinished() {
    return this.retrievalFinished;
  }

  editAttributes(inputId) {
    this.router.navigate(['editimageattr'], {
      queryParams:
      {
        id: inputId,
      }
    });
  }
}
