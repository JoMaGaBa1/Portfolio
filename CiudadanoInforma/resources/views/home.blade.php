@extends('layouts.app')

@section('title')Administración @endsection

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="row justify-content-center">
                <div class="col-sm-6 px-1 mb-2">
                    <div class="card">
                        <div class="card-header">
                            <strong>Tasa de incidencias en cada estado</strong>
                        </div>
                        <div class="card-body">
                            <div id="porcentajeXEstado">
                                {{ __('Gráfico de tarta') }}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 px-1 mb-2">
                    <div class="card">
                        <div class="card-header">
                            <strong>Tasa de incidencias de cada equipo</strong>
                        </div>
                        <div class="card-body">
                            <div id="incidenciasXEquipo">
                                {{ __('Gráfico de tarta') }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col px-1 mb-2">
                    <div class="card">
                        <div class="card-header">
                            <strong>Porcentaje de incidencias resueltas por cada equipo</strong>
                        </div>
                        <div class="card-body">
                            <div id="porcentajeXEquipo">
                                {{ __('Gráfico de barras') }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.highcharts.com/highcharts.js"></script>

<script type="text/javascript">
    Highcharts.chart('porcentajeXEstado', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b><br>{point.percentage:.2f} %',
                    distance: -50
                }
            }
        },
        series: [{
            name: 'Porcentaje',
            data: {!! json_encode(collect($porcentajeXEstado)) !!}
        }]
    });

    Highcharts.chart('incidenciasXEquipo', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b><br>{point.percentage:.2f} %',
                    distance: -50
                }
            }
        },
        series: [{
            name: 'Porcentaje',
            data: {!! json_encode(collect($incidenciasXEquipo)) !!}
        }]
    });

    Highcharts.chart('porcentajeXEquipo', {
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'Porcentaje'
            },
            max: 100
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.2f}%'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b>'
        },
        series: [
            {
                name: 'Incidencias resueltas',
                colorByPoint: true,
                data: {!! json_encode(collect($incidenciasXEquipoResueltas)) !!}
            }
        ]
    });
</script>
@endsection
