/*
e health system*/


function bpchart(data, cat, title) {
console.log(data);
	var testname = [];
	var testresult = [];
	var testunit = [];
	obj = {};
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	   var unit=data[i].name.split(" ");
	   testunit.push(unit[1]);
	   
	   data[i].code = unit[1];
	   
	   //data.push(obj)
	}
	
	console.log(data)
	    Highcharts.chart('bpanalyzer', {
	    
	    chart: {
	        type: 'bar'
	    },
	   /* title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	   /* tooltip: {
	        valueSuffix: testunit
	    },*/
	    tooltip: {
	        formatter: function(i) {
	        	console.log(this.points[0])
	        	console.log(this.points[0].series.userOptions.key[i])
	            return  '<span style="font-size:10px">' + this.points[0].key + '</span><table>' + this.points[0].series.userOptions.key[i] + '</table><br/>' + '<span style="color:' + this.points[0].series.color + '">' + this.points[0].series.name + '</span>: <b>' + this.points[0].y + '% </b>'
	        },
	        shared: true
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        key:testunit,
	    	name: '',
	        data: testresult
	        }]
	    
	});
	    console.log("test name"+testname);
}
function hematologyGraph(data, cat, title) {

console.log(data);
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup1', {
	    
	    chart: {
	        type: 'bar'
	    },
	   /* title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function immunoGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup13', {
	    
	    chart: {
	        type: 'bar'
	    },
	   /* title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function kidneyGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup3', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function lipidGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup4', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function liverGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup5', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function microGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup6', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function physiqueGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup7', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            //text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function sereoGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup8', {
	    
	    chart: {
	        type: 'bar'
	    },
	   /* title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function sugarGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup9', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mg/dL'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function urineGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup10', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	          //  text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function glucoseGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup11', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	          //  text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}
function electroGraph(data, cat, title) {
	var testname = [];
	var testresult = [];
	for (i = 0; i < data.length; i++) {
	   testname.push(data[i].key);
	   testresult.push(parseInt(data[i].name));
	}
	    Highcharts.chart('bloodgroup12', {
	    
	    chart: {
	        type: 'bar'
	    },
	    /*title: {
	        text: 'Historic World Population by Region'
	    },
	    subtitle: {
	        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
	    },*/
	    xAxis: {
	        categories: testname,
	        title: {
	            text: null
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	           // text: 'Test Graph',
	            align: 'high'
	        },
	        labels: {
	            overflow: 'justify'
	        }
	    },
	    tooltip: {
	        valueSuffix: ' mmHg'
	    },
	    plotOptions: {
	        bar: {
	            dataLabels: {
	                enabled: true
	            }
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'top',
	        x: -40,
	        y: 80,
	        floating: true,
	        borderWidth: 1,
	        backgroundColor:
	            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	        shadow: true
	    },
	    credits: {
	        enabled: false
	    },
	    series: [{
	        name: '',
	        data: testresult
	        }]
	});
}

function downloadChartPdf() {
    Highcharts.charts[0].exportChart({
        type: 'application/pdf'
    });
}