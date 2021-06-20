@extends('layouts.app')

@section('title')Acceso denegado @endsection

@section('content')
<div class="d-flex align-items-center justify-content-center">
    <div class="col-sm-4 mx-5">
        <h1>Error 403 - Acceso denegado</h1>
        <p>No tienes autorización para entrar a esta página. Si crees que es un error, contacta con algún administrador.</p>
        <p>Para acceder como ciudadano y poder realizar reportes, debes acceder al servicio desde la aplicación para Android.</p>
    </div>
</div>
@endsection
