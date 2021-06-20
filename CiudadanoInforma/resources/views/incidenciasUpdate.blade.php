@extends('layouts.app')

@section('title')Modificar incidencia @endsection

@section('content')
<script src="{{ asset('js/deleteFormConfirm.js') }}"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form method="post" action="{{ __('../../../incidencias/edit/') . $incidencia->id }}">
                @method('put')
                @csrf
                <div class="card">
                    <div class="card-header">
                        <div class="mt-2">
                            <h3>{{ __('Modificando incidencia') }}</h3>
                            <input type="hidden" name="id" value="{{ $incidencia->id }}">
                            <hr class="d-none d-sm-block">
                            <h4><strong>{{ __('#') . $incidencia->id . __(' - ') . $incidencia->titulo }}</strong></h4>
                        </div>
                        <div class="text-wrap">
                            <strong>{{ __('Ubicación:') }}</strong> {{ Str::ucfirst($incidencia->ubicacion) }}
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <label for="estado">Estado:</label>
                                <select id="estado" name="estado" class="form-control">
                                    @foreach($estados as $est)
                                        <option value="{{ $est->id }}" @if($est->id == $incidencia->estado()->get()->first()->id) selected @endif>{{ $est->nombre }}</option>
                                    @endforeach
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <label for="categoria">Categoría:</label>
                                <select id="categoria" name="categoria" class="form-control">
                                    @foreach($categorias as $cat)
                                        <option value="{{ $cat->id }}" @if($cat->id == $incidencia->categoria()->get()->first()->id) selected @endif>{{ $cat->nombre }}</option>
                                    @endforeach
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <label for="equipo">Equipo:</label>
                                <select id="equipo" name="equipo" class="form-control">
                                    @foreach($equipos as $equ)
                                        <option value="{{ $equ->id }}" @if($equ->id == $incidencia->equipo()->get()->first()->id) selected @endif>{{ $equ->nombre }}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        @foreach($incidencia->comentarios()->get() as $coment)
                            <div class="card mb-2 mt-3">
                                <div class="card-header">
                                    <div class="row">
                                        <div class="col-md-7">
                                            <strong>{{ __('#') . $coment->id . __(' - ') . $coment->user()->get()->first()->name }}</strong>
                                        </div>
                                        <div class="col-md-5 text-md-right">
                                            {{ $coment->created_at->format('j/m/Y - H:i:s') }}
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">{{ $coment->contenido }}</div>
                            </div>
                        @endforeach
                        <div class="card mb-2 mt-3">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-md-7">
                                        {{ __('Añadir comentario:') }}
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <input id="comentario" type="text" class="form-control" name="comentario" placeholder="Si lo deja vacío, se pondrá un comentario por defecto.">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">{{ __('Modificar') }}</button>
                        <button type="button" onclick="history.back()" class="btn d-block mx-auto">{{ __('Cancelar') }}</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
@endsection
