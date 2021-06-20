@extends('layouts.app')

@section('title'){{ $titulo }} @endsection

@section('content')
<script src="{{ asset('js/deleteFormConfirm.js') }}"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ $tituloTarjeta }}</div>
                <div class="card-body">
                    <div class="row font-weight-bold d-none d-sm-flex">
                        <div class="col-2">{{ __('ID') }}</div>
                        <div class="col-sm-5 col-lg-6">{{ __('Nombre') }}</div>
                        <div class="col-sm-5 col-lg-4">{{ __('Acciones') }}</div>
                    </div>
                    <hr class="d-none d-sm-block">
                    @foreach($elementos as $elemento)
                        <div id="{{ $elemento->id }}" class="row">
                            <div class="col-2">
                                {{ __('#') . $elemento->id }}
                            </div>
                            <div class="col-sm-5 col-lg-6">
                                @isset($id)
                                    @if($id == $elemento->id)
                                        <strong>{{ $elemento->nombre }}</strong>
                                    @else
                                        {{ $elemento->nombre }}
                                    @endif
                                @else
                                    {{ $elemento->nombre }}
                                @endisset
                            </div>
                            <div class="col-sm-5 col-lg-4 mb-3">
                                <a href="{{ $url . __('/edit/') . $elemento->id }}/view">
                                    {{ __('Editar') }}
                                </a>
                                @if(count($elemento->incidencias()->get()) < 1)
                                    {{ __(' - ') }}
                                    <form method="post" action="{{ $url . __('/delete/') . $elemento->id }}" class="d-inline">
                                        @csrf
                                        @method('delete')
                                        <input type="submit" data-name="{{ $elemento->nombre }}" value="Eliminar" class="btn btn-link p-0 btnEliminar">
                                    </form>
                                @endif
                            </div>
                        </div>
                    @endforeach
                    <a href="{{ $url . __('/create/view')}}">{{ __('Crear ') . $create }}</a>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
