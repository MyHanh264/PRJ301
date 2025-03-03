<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    .logo{
        width: 200px;
        height: 100px;
        object-fit: contain;
    }
    .header{
        background-color: bluevioletred;
        padding: 1rem 0;
        width: 100%;
        top: 0;

    }
    .container{
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 1rem;
    }
    .nav{
        display: flex;
        display: flex;
        justify-content: flex-start; 
        align-items: center;
        gap: 2rem; 
    }
    .logo{
        font-size: 1.5rem;
        font-weight: bold;
        text-decoration: none;
    }
    .menu{
        display: flex;
        list-style: none;
        gap: 2rem;
    }
    .menu-item a {
        xt-decoration: none;
        font-size: 1rem;
        transition: color 0.3s ease;
    }
    .menu-item a:hover {
        color: #3498db;
    }
</style>
<header class="header">
    <div class="container">
        <nav class="nav">
            <img src="img/logo.avif" alt="logo" class="logo"> 
            <ul class="menu">
                <li class="menu-item"><a>Tất cả sản phẩm</a></li>
                <li class="menu-item"><a>Nam</a></li>
                <li class="menu-item"><a>Nữ</a></li>
                <li class="menu-item"><a>Sale Off</a></li>
            </ul>
        </nav>

    </div>
</header>