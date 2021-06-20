@extends('layouts.app')

@section('title')Incidencias @endsection

@section('content')
<script src="{{ asset('js/deleteFormConfirm.js') }}"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <div class="card">
                <div class="card-header">{{ __('Incidencias registradas') }}</div>
                <div class="card-body">
                    @foreach($incidencias as $inc)
                        <div id="{{ $inc->id }}" class="card mb-3">
                            <div class="card-header">
                                <div class="row align-items-center">
                                    <div class="col-12 col-md-5">
                                        <img class="img-fluid" src="{{ __('../storage/') }}@isset($inc->archivo){{ $inc->archivo }}@else{{ __('cat') . $inc->categoria()->get()->first()->id . __('.png') }}@endisset" alt="{{ __('imagen') }}">
                                    </div>
                                    <div class="col-12 col-md-7">
                                        <div class="mt-1">
                                            <h3><strong>{{ __('#') . $inc->id . __(' - ') . $inc->titulo }}</strong></h3>
                                        </div>
                                        <div class="text-wrap">
                                            <strong>{{ __('Ubicación:') }}</strong> {{ Str::ucfirst($inc->ubicacion) }}
                                        </div>
                                        <a class="btn btn-link p-0" href="{{ __('incidencias/edit/') . $inc->id . __('/view') }}">{{ __('Modificar') }}</a>
                                        {{ __(' - ') }}
                                        <form method="post" action="{{ __('incidencias/delete/') . $inc->id }}" class="d-inline">
                                            @csrf
                                            @method('delete')
                                            <input type="submit" data-name="{{ __('#') . $inc->id }}" value="Eliminar incidencia" class="btn btn-link p-0 btnEliminar">
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <h4>Información general:</h4>
                                <div class="row mx-md-3 mt-2">
                                    <div class="col-12 col-md-6 my-auto">
                                        <strong>{{ __('Estado:') }}</strong> {{ Str::ucfirst($inc->estado()->get()->first()->nombre) }} <span class="text-nowrap">{{ __(' (') . $inc->updated_at->format('j/m/Y - H:i:s') . __(')') }}</span><br>
                                        <strong>{{ __('Categoría:') }}</strong> {{ Str::ucfirst($inc->categoria()->get()->first()->nombre) }}<br>
                                        <strong>{{ __('Equipo responsable:') }}</strong> {{ Str::ucfirst($inc->equipo()->get()->first()->nombre) }}<br>
                                    </div>
                                    <div class="col-12 col-md-6 my-auto">
                                        <strong>{{ __('Usuarios participantes:') }}</strong>
                                        <ul class="mb-0">
                                            @foreach($inc->users()->get() as $user)
                                                <li>
                                                    <a class="p-0 text-reset text-decoration-none" href="{{ __('usuarios?id=') . $user->id . __('#') . $user->id }}">
                                                        {{ $user->name . __(' - ') . $user->email }}
                                                    </a>
                                                </li>
                                            @endforeach
                                        </ul>
                                    </div>
                                </div>
                                @foreach($inc->comentarios()->get() as $coment)
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
                                        @if($coment != $inc->comentarios()->get()->first())
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
                    @endforeach
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
