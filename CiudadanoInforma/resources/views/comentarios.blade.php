@extends('layouts.app')

@section('title')Comentarios @endsection

@section('content')
<script src="{{ asset('js/deleteFormConfirm.js') }}"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card">
                <div class="card-header">{{ __('Comentarios registrados') }}</div>
                <div class="card-body">
                    @foreach($comentarios as $coment)
                        <div id="{{ $coment->id }}" class="card mb-2">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="row mx-0 mt-1">
                                            <h5><strong>{{ __('Comentario #') . $coment->id }}</strong></h5>
                                        </div>
                                        <div class="row mx-0">
                                            <p class="m-0">
                                                {{ __('Incidencia:') }}
                                                <a class="btn btn-link p-0" href="{{ __('incidencias#') . $coment->incidencia()->get()->first()->id }}">
                                                    {{ __('#') . $coment->incidencia()->get()->first()->id . __(' - ') . $coment->incidencia()->get()->first()->titulo }}
                                                </a>
                                                <br>
                                                {{ __('Usuario: ') }}
                                                <a class="btn btn-link p-0" href="{{ __('usuarios?id=') . $coment->user()->get()->first()->id . __('#') . $coment->user()->get()->first()->id }}">
                                                    {{ __('#') . $coment->user()->get()->first()->id . __(' - ') . $coment->user()->get()->first()->name }}
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-md-5 text-md-right my-auto text-nowrap">
                                        {{ $coment->created_at->format('j/m/Y - H:i:s') }}
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">{{ $coment->contenido }}</div>
                            @if($coment != $coment->incidencia()->get()->first()->comentarios()->get()->first())
                                <div class="card-footer">
                                    <form method="post" action="{{ __('comentarios/delete/') . $coment->id }}" class="d-inline">
                                        @csrf
                                        @method('delete')
                                        <input type="submit" data-name="{{ __('#') . $coment->id }}" value="Eliminar comentario" class="btn btn-link p-0 btnEliminar">
                                    </form>
                                </div>
                            @endif
                        </div>
                    @endforeach
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
