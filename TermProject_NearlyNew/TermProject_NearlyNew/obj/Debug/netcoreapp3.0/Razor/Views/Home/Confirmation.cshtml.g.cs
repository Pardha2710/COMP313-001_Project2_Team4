#pragma checksum "C:\Users\manmeet\Desktop\PROJECT\TermProject_NearlyNew\TermProject_NearlyNew\Views\Home\Confirmation.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "bfbe6e0927f8104d3bda5073ef03a20ef2069454"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Home_Confirmation), @"mvc.1.0.view", @"/Views/Home/Confirmation.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\manmeet\Desktop\PROJECT\TermProject_NearlyNew\TermProject_NearlyNew\Views\_ViewImports.cshtml"
using TermProject_NearlyNew.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"bfbe6e0927f8104d3bda5073ef03a20ef2069454", @"/Views/Home/Confirmation.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"4559fd688e723cc25dcdc9ec6e8cd9057927c496", @"/Views/_ViewImports.cshtml")]
    public class Views_Home_Confirmation : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 2 "C:\Users\manmeet\Desktop\PROJECT\TermProject_NearlyNew\TermProject_NearlyNew\Views\Home\Confirmation.cshtml"
  
    ViewData["Title"] = "Confirmation";
    Layout = "~/Views/Shared/_Layout.cshtml";

#line default
#line hidden
#nullable disable
            WriteLiteral(@"
<!--================ confirmation part start =================-->
<section class=""confirmation_part section_padding"">
    <div class=""container"">
        <div class=""row"">
            <div class=""col-lg-12"">
                <div class=""confirmation_tittle"">
                    <span>Thank you. Your order has been received.</span>
                </div>
            </div>
            <div class=""col-lg-6 col-lx-4"">
                <div class=""single_confirmation_details"">
                    <h4>order info</h4>
                    <ul>
                        <li>
                            <p>order number</p><span>: 60235</span>
                        </li>
                        <li>
                            <p>data</p><span>: Oct 03, 2017</span>
                        </li>
                        <li>
                            <p>total</p><span>: USD 2210</span>
                        </li>
                        <li>
                            <p>mayment methord</p><sp");
            WriteLiteral(@"an>: Check payments</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class=""col-lg-6 col-lx-4"">
                <div class=""single_confirmation_details"">
                    <h4>Billing Address</h4>
                    <ul>
                        <li>
                            <p>Street</p><span>: 56/8</span>
                        </li>
                        <li>
                            <p>city</p><span>: Los Angeles</span>
                        </li>
                        <li>
                            <p>country</p><span>: United States</span>
                        </li>
                        <li>
                            <p>postcode</p><span>: 36952</span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class=""col-lg-6 col-lx-4"">
                <div class=""single_confirmation_details"">
                    <h4>shipping ");
            WriteLiteral(@"Address</h4>
                    <ul>
                        <li>
                            <p>Street</p><span>: 56/8</span>
                        </li>
                        <li>
                            <p>city</p><span>: Los Angeles</span>
                        </li>
                        <li>
                            <p>country</p><span>: United States</span>
                        </li>
                        <li>
                            <p>postcode</p><span>: 36952</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class=""row"">
            <div class=""col-lg-12"">
                <div class=""order_details_iner"">
                    <h3>Order Details</h3>
                    <table class=""table table-borderless"">
                        <thead>
                            <tr>
                                <th scope=""col"" colspan=""2"">Product</th>
                            ");
            WriteLiteral(@"    <th scope=""col"">Quantity</th>
                                <th scope=""col"">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th colspan=""2""><span>Pixelstore fresh Blackberry</span></th>
                                <th>x02</th>
                                <th> <span>$720.00</span></th>
                            </tr>
                            <tr>
                                <th colspan=""2""><span>Pixelstore fresh Blackberry</span></th>
                                <th>x02</th>
                                <th> <span>$720.00</span></th>
                            </tr>
                            <tr>
                                <th colspan=""2""><span>Pixelstore fresh Blackberry</span></th>
                                <th>x02</th>
                                <th> <span>$720.00</span></th>
                            </tr>
     ");
            WriteLiteral(@"                       <tr>
                                <th colspan=""3"">Subtotal</th>
                                <th> <span>$2160.00</span></th>
                            </tr>
                            <tr>
                                <th colspan=""3"">shipping</th>
                                <th><span>flat rate: $50.00</span></th>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th scope=""col"" colspan=""3"">Quantity</th>
                                <th scope=""col"">Total</th>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================ confirmation part end =================-->
");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591
