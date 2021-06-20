@extends('layouts.app')

@section('title'){{ $titulo }} @endsection

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ $tituloTarjeta }}</div>
                <div class="card-body">
                    @if($tipo == 1)
                        <form method="post" action="{{ __('../../') . $url . '/' . 'create' }}">
                        @method('post')
                    @else
                        <form method="post" action="{{ __('../../../') . $url . '/' . 'edit' . '/' . $id }}">
                        @method('put')
                    @endif
                        @csrf
                        <div class="form-group row">
                            <input type="hidden" name="id" value="{{ $id }}">
                            <label for="nombre" class="col-md-4 col-form-label text-md-right">{{ __('Nombre') }}</label>
                            <div class="col-md-6">
                                <input id="nombre" type="text" class="form-control" name="nombre" @if($tipo === 1) placeholder="{{ $prev }}" @else value="{{ $prev }}" @endif required autofocus>
                            </div>
                        </div>
                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    @if($tipo == 1)
                                        {{ __('Crear') }}
                                    @else
                                        {{ __('Actualizar') }}
                                    @endif
                                </button>
                                <button type="button" onclick="history.back()" class="btn">{{ __('Cancelar') }}</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
