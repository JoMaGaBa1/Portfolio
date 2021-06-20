@extends('layouts.app')

@section('title')Usuarios @endsection

@section('content')
<script src="{{ asset('js/deleteFormConfirm.js') }}"></script>
<div class="container">
    <div class="row justify-content-center">
        <div class="col">
            <div class="card">
                <div class="card-header">{{ __('Usuarios registrados') }}</div>
                <div class="card-body">
                    <div class="row font-weight-bold d-none d-sm-flex">
                        <div class="col-sm-6 col-md-4 col-lg-6">{{ __('Usuario') }}</div>
                        <div class="col-sm-3 col-md-4 col-lg-3 text-center">{{ __('Administrador') }}</div>
                        <div class="col-sm-3 col-md-4 col-lg-3">{{ __('Acciones') }}</div>
                    </div>
                    <hr class="d-none d-sm-block">
                    @foreach($usuarios as $usuario)
                        <div id="{{ $usuario->id }}" class="row">
                            <div class="col-sm-6 col-md-4 col-lg-6">
                                @isset($id)
                                    @if($id == $usuario->id)
                                        <strong>{{ $usuario->name . __(' - ') }}<i>{{ $usuario->email }}</i></strong>
                                    @else
                                        {{ $usuario->name . __(' - ') }}<i>{{ $usuario->email }}</i>
                                    @endif
                                @else
                                    {{ $usuario->name . __(' - ') }}<i>{{ $usuario->email }}</i>
                                @endisset
                                @if($usuario->isAdmin())
                                    <small class="d-inline d-sm-none">{{ __(' - ') }}</small>
                                    <small class="font-italic d-inline d-sm-none">{{ __('Administrador') }}</small>
                                @endif
                            </div>
                            <div class="col-sm-3 col-md-4 col-lg-3 d-none d-sm-flex">
                                @if($usuario->isAdmin())
                                    <span class="mx-auto oi oi-check text-success"></span>
                                @else
                                    <span class="mx-auto oi oi-x text-danger"></span>
                                @endif
                            </div>
                            <div class="col-sm-3 col-md-4 col-lg-3 mb-3">
                                @if($usuario->email == Auth::user()->email)
                                    <div class="disabled">
                                        {{ __('(Usuario actual)') }}
                                    </div>
                                @else
                                    <form method="post" action="{{ __('usuarios/delete/') . $usuario->id }}" class="d-inline">
                                        @csrf
                                        @method('delete')
                                        <input type="submit" data-name="{{ $usuario->name }}" value="Eliminar" class="btn btn-link p-0 btnEliminar">
                                    </form>
                                @endif
                            </div>
                        </div>
                    @endforeach
                    <a href="register">{{ __('Crear usuario') }}</a>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
