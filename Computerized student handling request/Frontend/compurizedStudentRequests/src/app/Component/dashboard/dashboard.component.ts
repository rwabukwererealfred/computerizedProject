import { Component, OnInit } from '@angular/core';
import { DashboardSService } from 'src/app/Service/dashboard-s.service';
import * as Chart from 'chart.js';
import { RegistrationService } from 'src/app/Service/registration.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  private transcriptDashPercentage: any = [];
  private recommandationDashPercentage: any = [];
  private A1DashPercentage: any = [];
  private COADashPercentage: any = [];
  private transcript: number;
  private reco: number;
  private A1: number;
  private COA: number;
  private staffList:any=[];
  private studentList:any=[];
  constructor(private dashService: DashboardSService, private registrationService:RegistrationService) {
  }
  ngOnInit() {

    console.log("recoss: " + this.reco);
    this.transcriptPercentage();
    this.A1Percentage();
    this.recommandationPercentage();
    this.COAPercentage();
    this.chartPage();
    this.stundets();
    this.staffs();
  }
  stundets(){
    this.registrationService.studentList().subscribe(res=>{
      this.studentList = res;
    }, error=>console.log(error));
  }
  staffs(){
    this.registrationService.staffList().subscribe(res=>{
      this.staffList = res;
    },error=>console.log(error));
  }

  transcriptPercentage() {
    this.dashService.transcriptDash().subscribe(res => {
      this.transcriptDashPercentage = res;
    }, error => {
      console.log(error);
    })
  }
  recommandationPercentage() {
    this.dashService.recommandationDash().subscribe(res => {
      this.recommandationDashPercentage = res;

    }, error => {
      console.log(error);
    })

  }
  A1Percentage() {
    this.dashService.A1Dash().subscribe(res => {
      this.A1DashPercentage = res;
    }, error => {
      console.log(error);
    })
  }
  COAPercentage() {
    this.dashService.COADash().subscribe(res => {
      this.COADashPercentage = res;
    }, error => {
      console.log(error);
    })
  }

  chartPage() {
    this.dashService.recommandationDash().subscribe(res => {

      var myChart = new Chart("myChart", {
        type: 'bar',
        data: {
          labels: ['Recommandation', 'Transcript', 'A1', 'Certificate of Attendance', 'Internship'],
          datasets: [{
            label: 'Student Request Rating',
            data: [this.recommandationDashPercentage[1], this.transcriptDashPercentage[1],
            this.A1DashPercentage[1], this.COADashPercentage[1], 0],
            backgroundColor: [
              'rgba(255, 99, 132, 100)',
              'rgba(54, 162, 235, 100)',
              'rgba(255, 206, 86, 100)',
              'rgba(100, 100, 192, 100)',
              'rgba(153, 102, 255, 100)'
              
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(100, 100, 192, 100)',
              'rgba(153, 102, 255, 1)'
             
            ],
           
            borderWidth: 2
           
          }]
        },
        
        options: {
          scales: {
            yAxes: [{
              ticks: {
               
                max:100,
                min:0,
                autoSkipPadding:20
                
                
              }
            }]
          }
        }
      });
    }, error => {
      console.log(error);
    })

  }


}
