<%-- 
    Document   : footer
    Created on : Feb 26, 2025, 4:22:53 PM
    Author     : hanhhee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .footer{
        box-sizing: border-box;
        background-color: #933232;
        color: white;
        padding: 10px;
        margin: 0;
        width: 100%;
    }
    .footer-container{
        display: flex;
        justify-content: space-between;
        max-width:  1320px;
        margin: 0 auto;
    }
    .footer-session{
        padding: 10px;
        margin: 0;
        padding-top: 5px;
        text-decoration: none;
        color: white;
    }
    .footer-session h3:hover,
    .footer-session p:hover{
        text-decoration: underline;
        text-underline-offset: 3px; 
    }
    .social{
        display: flex;
        gap: 20px;
    }
    .social img:hover{
        transform: scale(1.1);
    }
    .footer-copyright{
        box-sizing: border-box;
        display: flex;
        justify-content: space-between;
        border-top: 1px solid white;
        margin: 0;
        width: 100%;
        padding: 10px;
    }

</style>
<footer class="footer">
    <div class="footer-container">   
        <div class="footer-session">
            <h3>Customer support</h3>
            <p>FAQ</p>
            <p>Terms and conditions</p>
            <p>Shipping & Returns</p>
            <p>Privacy policy</p>
        </div>
        <div class="footer-session">
            <h3>Information</h3>
            <p>Size Guide</p>
            <p>Feedback</p>
        </div>
        <div class="footer-session">
            <h3>Contact us</h3>
            <p>Address: D1 Street, Long Thanh My Ward, Thu Duc City</p>
            <p>Email: bloomonfoot@gmail.com</p>
            <div class="social">
                <a href="#">
                    <img src="img/facebook.svg" alt="Facebook" width="30">
                </a>
                <a href="#">
                    <img src="img/instagram.svg" alt="Instagram" width="30">
                </a>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="footer-small">
            <p>Established by Bloom on Foot</p>
        </div>
        <div class="footer-small">
            <p>VIET NAM</p>
        </div>
        <div class="footer-small">
            <p>&copy; 2025 Viet Nam, INC. All rights reserved.</p>
        </div>
    </div>
</footer>