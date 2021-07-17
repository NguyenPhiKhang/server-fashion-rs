package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICategoryController;
import com.khangse616.serverfashionrs.mappers.impl.CategoryScreenDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.CategoryDetailDTOMapper;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.dto.CategoryScreenDTO;
import com.khangse616.serverfashionrs.models.dto.CategoryDetailDTO;
import com.khangse616.serverfashionrs.models.dto.InputCategoryDTO;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CategoryController implements ICategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<Set<Category>> getCategoriesByParentCategory(int parentId) {
        Set<Category> categoryScreenDTO = categoryService.findCategoriesByParentId(parentId);
        return ResponseEntity.ok().body(categoryScreenDTO);
    }

    @Override
    public String autoCreatePath() {
        List<Category> categories = categoryService.findAllCategories();

        categories.forEach(c -> {
            StringBuilder categoriesStr = new StringBuilder();
            Category category = c;

            for (int i = c.getLevel(); i >= 0; i--) {
                categoriesStr.insert(0, "/" + category.getId());
                category = category.getParentCategory();
            }
            categoriesStr.insert(0, 0);

            c.setPath(categoriesStr.toString());
        });

        categoryService.saveAll(categories);

        return "done";
    }

    @Override
    public String autoAddIconCategories(int idCategory) {
        return categoryService.autoSetIconCategory(idCategory);
    }

    @Override
    public String AddIconCategory() {
        String[] listIconLink = {"https://sieuthihangmy.com.vn/upload/images/2016/Quan_Ao/Nam/Ao_thun/nike/nike_mens_icon_heather_polo/bright_mandarinwhiteflat_silver/1.jpg",
                "https://storage.googleapis.com/cdn-parisfashionshops/v2/front/homepage/icons/pe2021/tops.jpg",
                "https://cf.shopee.vn/file/b7252ce00c28c9285c46b3f8af963bff",
                "https://cf.shopee.vn/file/747ea7ee44f0f7fca13ff431744955e7",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/8/_/quan_the_thao_bong_ro_alien_amour_s004_da02.jpg",
                "https://product.hstatic.net/1000008082/product/1_7919d6fba8c34b078bcae0ee774422fd_master.jpg",
                "https://media3.scdn.vn/img3/2019/6_20/rJOzlu_simg_de2fe0_500x500_maxb.jpg",
                "https://bizweb.dktcdn.net/100/349/017/products/v005-do.jpg?v=1580802653493",
                "http://www.gaugaushop.com/plugins/responsive_filemanager/source/san%20pham/VayDam/GD916/750-dam-nu-voan-daopho-dethuong.jpg",
                "https://blingerie.vn/wp-content/uploads/2019/11/78410942_2834900366542165_7613828382639259648_o-1-800x800.jpg",
                "https://cf.shopee.vn/file/173a7d57c34606701d7bf1b173e19f95",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/dep_nam_quai_ngang_nam_tinh_f380.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/vi_nam_vnl_da_forboss_vn2znd16d_tang_kem_vi_card_4ae5.jpg",
                "https://vuadasaigon.com/images/detailed/2/vi_ngan_da_nam_vd07_4.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/5/_/that_lung_nam_da_bo_lw_ca_tinh_057f.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/quan_tay_nam_cong_so_vu_tuan_xl0074_752e.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/quan_cap_di_bien_the_sea_244f.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/non_nam_nu_the_thao_nd_1996_3e84.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/kinh_mat_jubilant_unisex_j45073m_pnkpk_58de.jpg",
                "https://donghobaothanh.vn/uploads/avt_Teintop_T7009_xanh.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/combo_5_doi_vo_nam_co_ngan_a3bf.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/ao_so_mi_nam_dai_tay_caro_vu_tuan_0055_999e.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/ao_thun_nam_the_thao_nang_dong_45d8.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/360x420/7b8fef0172c2eb72dd8fd366c999954c/6/_/ao_khoac_du_nam_nu_phong_cach_a035.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/8/_/ao_khoac_unisex_phoi_chu_ca_tinh_7cfb.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/360x420/7b8fef0172c2eb72dd8fd366c999954c/1/_/dep_got_vuong_ban_lon_sulily_dg1i17_7931.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/dep_nu_fnirvara_dinh_dinh_sanh_dieu_6e21.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/combo_balo_tui_ipad_vi_nu_cham_bi_thoi_trang_b357.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/360x420/7b8fef0172c2eb72dd8fd366c999954c/1/_/tui_deo_cheo_nu_2_day_khoa_elmi_mt02_bd81.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/9/_/combo_2_that_lung_nu_hobby_w009_sanh_dieu_c731.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/combo_2_non_coi_nu_vanh_song_47ee.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/5/_/mat_kinh_nu_phong_cach_han_quoc_b353.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/_/dong_ho_nu_julius_ja746_hong_f2d9.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/6/_/ao_lot_nu_jl_1166_thoi_trang_fedb.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/quan_gen_nit_bung_nu_gp_912_sexy_69ad.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_vo_nu_zappy_thoi_trang_046f.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/combo_3_quan_lot_nam_tman_ca_tinh_015_b244.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/_/combo_cham_soc_giay_shucare_sccombo7_b82e.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/_/khan_choang_nu_caro_hoa_tiet_hoa_3cec.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_short_jeans_nu_aaa_jeans_xb_bbf8.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_gia_vay_dinh_nut_ben_hong_xinh_xan_177a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/6/_/ao_so_mi_nu_phoi_tui_2_ben_6457.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/ao_trung_nien_nu_nhun_lai_alx_37_f9bd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/ao_khoac_nu_phoi_da_nang_dong_920b.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/8/_/ao_vest_nu_thanh_lich_d1f1.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/dam_suong_dinh_hoa_tre_trung_cab1.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/dam_xoe_cat_laser_xinh_xan_a0dd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/ao_khoac_len_nu_thoi_trang_6454.jpg",
                "https://img.zanado.com/media/catalog/product/cache…mat_day_bac_phi_thuy_cham_ho_lo_hnj_5653_7567.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/combo_cham_soc_giay_shucare_sccombo5_f40a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/vay_chong_nang_kaki_cham_bi_sg_f8fd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_boi_nam_speed_nang_dong_bf3d.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/bo_bikini_lafonci_sexy_lfb16_0f91.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/dam_ngu_hisexy_vien_chan_ren_t202_dbd1.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/5/_/dam_xoe_xep_ly_phoi_do_cbd4.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/dam_maxi_hoa_tiet_xinh_xan_0c82.jpg",
                "https://sakurafashion.vn/upload/sanpham/large/7008-ao-khoac-nu-mau-tron-1.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/_/bo_ao_dai_hinastyle_sang_trong_1719_7a8b.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_culottes_xep_ly_thoi_trang_8660.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/chan_vay_jean_phoi_tui_thoi_trang_ef51.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/2/chan_vay_xoe_co_dien_408a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/set_ao_soc_va_quan_culottes_thoi_trang_05aa.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/8/_/bo_do_nu_mac_nha_happy_lady_h1576_4961.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/6/_/jumpsuit_2_in_1_sanh_dieu_a3c1.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/ao_cardigan_nam_phong_cach_han_quoc_303c.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/ao_khoac_nam_akuba_cao_cap_3309_f9ec.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/9/_/ao_khoac_kaki_pilot_landing_tre_trung_87ce.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_jean_nam_vien_chi_noi_mot_tre_84cd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_tay_nu_cong_so_thanh_lich_e005.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/giay_sandal_nam_nang_dong_a099.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/giay_sandal_nu_de_vuong_thoi_trang_4b64.jpg",
                "https://motalo.vn/wp-content/uploads/2018/05/IMG_0549.jpg",
                "https://vn-test-11.slatic.net/p/0b0429c455a546885ba6a10cff6105a1.jpg_320x320.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/5/_/combo_3_quan_lot_nam_tman_060_f6bf.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/360x420/7b8fef0172c2eb72dd8fd366c999954c/1/_/set_do_lot_ren_xanh_goi_cam_86a4.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/5/_/cap_xach_nam_cong_so_cnt_lich_lam_1cd0.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/balo_nam_nu_doi_canh_thien_than_f109.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/7/_/tui_xach_nu_mini_2_ngan_sang_trong_734a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/1/balo_nu_hoa_tiet_tho_cam_doc_dao_74cd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/set_do_lot_ren_xanh_goi_cam_8aa9.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/ao_khoac_xo_ngon_nu_gray_6370.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/ao_khoac_bomber_can_de_blanc_d7049_e587.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/8/dam_bau_phoi_no_xinh_xan_063_6f8a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/ao_chong_nang_hoa_tiet_hoa_xuan_thoi_trang_a6f1.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/ao_khoac_nu_xo_ngon_thoi_thuong_f348.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/set_bo_ao_quan_phoi_day_keo_thoi_trang_2fc4.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/giay_tay_nam_tomani_phong_cach_331_5388.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/giay_tuvis_unisex_xo_xam_m38_e030.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/giay_luoi_nam_phoi_day_zana_grey_853c.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/6/_/giay_cao_got_mh_thanh_lich_265d.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/3/_/giay_bup_be_tuvis_kid_mau_hong_01fa.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/giay_cot_day_nu_kim_tuyen_quickfree_w160505_d1ef.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/quan_jean_nam_xuoc_cao_945112113_thoi_trang_b356.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/quan_dai_kaki_nam_onymax_lich_lam_df0a.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/t/r/dong_ho_nam_day_da_227_thoi_thuong_65ec.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/dong_ho_cam_ung_skmei_0950_dd8e.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/6/_/dong_ho_nu_skmei_sk089_nu_tinh_3b85.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/dong_ho_cam_ung_nu_day_plastic_valook_ge084_3df1.jpg",
                "https://vn-test-11.slatic.net/p/fbdea90309cddd58ee4c7ba558179794.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/360x420/7b8fef0172c2eb72dd8fd366c999954c/1/_/dam_cong_so_fogin_thanh_lich_a6ad.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/1/9/bo_do_the_thao_nang_dong_colo_cl1703339_8cd5.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/5/_/vong_tay_pha_le_ao_10_ly_8022.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/mat_day_bac_phi_thuy_cham_qua_tao_hnj_5648_cfda.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/bo_trang_suc_bac_925_co_4_la_f918.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/ao_dai_cach_tan_ket_hoa_hong_dn_68fd.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/ao_kieu_nu_tay_loe_phoi_no_tay_11db.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/_/khan_choang_hoa_tiet_con_ho_3166.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/9/_/quan_gen_bung_imerry_vq035_cao_cap_6083.jpg",
                "https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/3/_/kem_duong_lam_mo_nep_nhan_vung_mat_its_well_plus_cpec_c07c.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/kem_chong_nang_duong_trang_da_devona_spf50pa_its_well_plus_spfc_b192.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/4/_/kem_tay_trang_perfect_perfect_cleansing_cream_graisset_2079.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/2/_/sua_duong_the_organic_tinh_chat_gao_lut_mat_ong_graisset_62bb.jpg",
                "http://img.zanado.com/media/catalog/product/cache/all/thumbnail/255x298/7b8fef0172c2eb72dd8fd366c999954c/1/2/nuoc_hoa_nu_ahaperfumes_aha691_carolina_herrera_212_sexy_30ml_9a77.jpg"};

        return categoryService.addIconCategories(listIconLink);
    }

    @Override
    public List<Category> getCategoriesByLevel(int level) {
        return categoryService.findCategoryByLevel(level);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryService.findCategoryById(id);
    }

    @Override
    public CategoryDetailDTO getPathCategory(int id) {
        return new CategoryDetailDTOMapper().mapRow(categoryService.findCategoryById(id));
    }

    @Override
    public List<CategoryScreenDTO> getAllCategories(int page, int pageSize, String search) {
        return categoryService.findAllCategoriesOrderByLevel(page, pageSize, search).stream().map(v->new CategoryScreenDTOMapper().mapRow(v)).collect(Collectors.toList());
    }

    @Override
    public int countCategories(String search) {
        return categoryService.countCategories(search);
    }

    @Override
    public String createNewCategory(InputCategoryDTO inputCategory) {
        categoryService.createNewCategory(inputCategory);
        if(inputCategory.getId()==0)
            return "Tạo danh mục thành công!";
        else return "Cập nhật thành công!";
    }

    @Override
    public ResponseEntity<ResponseMessage<Integer>> deleteCategoryById(int idCategory) {
        try{
            categoryRepository.deleteById(idCategory);
            return ResponseEntity.ok().body(new ResponseMessage<>("Xoá thành công", 1));
        }catch (Exception ex){
            return ResponseEntity.ok().body(new ResponseMessage<>("Không xoá được", -1));
        }
    }

    //    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryScreenDTO>> getCategoriesLevel1(@RequestParam(value = "level", required = false, defaultValue = "0") int level){
//        List<CategoryScreenDTO> categoryScreenDTO = level!=0?categoryService.findCategoryByLevel(level).stream().map(value->new CategoryScreenDTOMapper().mapRow(value)).collect(Collectors.toList())
//                : categoryService.findAllCategories().stream().map(value->new CategoryScreenDTOMapper().mapRow(value)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(categoryScreenDTO);
//    }


//    @Override
//    public ResponseEntity<Set<String>> getRecommendSearch(String keyword) {
//        return ResponseEntity.ok().body(categoryService.recommendSearch(keyword));
//    }
}
